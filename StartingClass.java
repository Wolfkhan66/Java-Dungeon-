import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

public class StartingClass extends Applet implements Runnable, KeyListener {

    enum GameState {
        Running, Dead
    }
    GameState state = GameState.Running;

    private static Camera camera;
    private static Player player;
    private static Minimapplayer minimapplayer;
    public static Image image, character, tile,bottomleftcorner, bottomrightcorner,bottomwall,leftwall,rightwall,topleftcorner,toprightcorner,topwall ,minimap, door1, door2, goblin, mapplayer ;
    private Graphics second;
    private URL base;

    private ArrayList<Map> maparray = new ArrayList<Map>();
    private ArrayList<Map> minimaparray = new ArrayList<Map>();
    public static ArrayList<Enemy> enemyarray = new ArrayList<Enemy>();

    public static int area;
    public static int score = 0;
    public static boolean roomchange = true;
    public static boolean minimapOn = false;

    private Font font = new Font(null, Font.BOLD, 30);
    Random r = new Random();

    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Map Alpha");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Image Setups
        mapplayer = getImage(base, "data/Minimap/character.png");
        character = getImage(base, "data/Player/Player.png");
        minimap = getImage(base, "data/Minimap/tile.png");
        door1 = getImage(base, "data/Doors/XDoorsClosed.png");
        door2 = getImage(base, "data/Doors/YDoorsClosed.png");
        goblin = getImage(base, "data/Enemies/Goblin.png");

        bottomleftcorner = getImage(base, "data/World1/bottomleftcorner.png");
        bottomrightcorner = getImage(base, "data/World1/bottomrightcorner.png");
        bottomwall = getImage(base, "data/World1/bottomwall.png");
        leftwall = getImage(base, "data/World1/leftwall.png");
        rightwall = getImage(base, "data/World1/rightwall.png");
        topleftcorner = getImage(base, "data/World1/topleftcorner.png");
        toprightcorner = getImage(base, "data/World1/toprightcorner.png");
        topwall = getImage(base, "data/World1/topwall.png");

    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        if (state == GameState.Dead)
        {
            thread.stop();
        }

        state = GameState.Running;
        roomchange = true;

        minimapplayer = new Minimapplayer();
        player = new Player();
        camera = new Camera();
        thread.start();
        area = Map.area;
    }

    private void loadMap(String filename, int room) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;
        int[][]map = {{ 4, 10,  7,  3,  6,  10,  7,  6, 12,  2},
                { 3,  4, 13,  5, 15, 12, 13, 11, 14,  2},
                {11,  7,  5,  7,  5, 15, 15,  8,  3,  3},
                { 5, 14,  2,  5,  7, 11, 14, 10,  8,  9},
                { 3,  4, 12, 12, 13, 11,  7,  4, 10, 13},
                { 1,  6, 14, 14,  8,  9,  5,  2,  3,  9},
                { 6, 15,  7,  6, 10, 15, 10,  2,  9,  1},
                {11, 14, 15, 15, 12, 15,  7,  3,  5,  7},
                { 9,  3, 11, 15, 15, 13,  5, 15,  7,  1}, 
                { 1,  1,  1,  1,  1,  5,  2,  5, 14,  2}} ;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }

        for (int x = 0; x <= 9 ; x++)
        {
            for (int y = 0; y <= 9 ; y++)
            {
                if ( map[x][y] == room)
                {
                    height = lines.size();
                    for (int j = 0; j < 12; j++) {
                        String line = (String) lines.get(j);
                        for (int i = 0; i < width; i++) {
                            System.out.println(i + "is i ");

                            if (i < line.length()) {
                                char ch = line.charAt(i);
                                Map t = new Tile((40 * i) + (x * 800), (40 * j) + (y * 480), Character.getNumericValue(ch));
                                maparray.add(t);
                                if( ch == 'a')
                                {
                                    t = new Door ( 320  + (x * 800),0  + (y * 480),4); // top
                                    maparray.add(t);
                                }
                                if( ch == 'b')
                                {
                                    t = new Door ( 0 + (x * 800),160 + (y * 480),2); // left
                                    maparray.add(t);
                                }
                                if( ch == 'c')
                                {
                                    t = new Door ( 760 + (x * 800),160 + (y * 480),3); // right
                                    maparray.add(t);
                                }
                                if( ch == 'd')
                                {
                                    t = new Door ( 320 + (x * 800),440 + (y * 480) ,5); // bottom
                                    maparray.add(t);
                                }
                            }
                        }
                    }
                }
            }

        }}
    private void loadMiniMap(String filename, int room) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;
        int[][]map = {{ 4, 10,  7,  3,  6,  10,  7,  6, 12,  2},
                { 3,  4, 13,  5, 15, 12, 13, 11, 14,  2},
                {11,  7,  5,  7,  5, 15, 15,  8,  3,  3},
                { 5, 14,  2,  5,  7, 11, 14, 10,  8,  9},
                { 3,  4, 12, 12, 13, 11,  7,  4, 10, 13},
                { 1,  6, 14, 14,  8,  9,  5,  2,  3,  9},
                { 6, 15,  7,  6, 10, 15, 10,  2,  9,  1},
                {11, 14, 15, 15, 12, 15,  7,  3,  5,  7},
                { 9,  3, 11, 15, 15, 13,  5, 15,  7,  1}, 
                { 1,  1,  1,  1,  1,  5,  2,  5, 14,  2}} ;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }

        for (int x = 0; x <= 9 ; x++)
        {
            for (int y = 0; y <= 9 ; y++)
            {
                if ( map[x][y] == room)
                {
                    height = lines.size();
                    for (int j = 0; j < 3; j++) {
                        String line = (String) lines.get(j);
                        for (int i = 0; i < width; i++) {
                            System.out.println(i + "is i ");
                            if (i < line.length()) {
                                char ch = line.charAt(i);
                                Map t = new Tile ((10 * i )+ (x * 30) ,(10 * j) + (y * 30), Character.getNumericValue(ch));
                                minimaparray.add(t);

                            }
                        }
                    }
                }
            }

        }

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void run() {

        if (state == GameState.Running) {
            while (true) {

                ArrayList projectiles = player.getProjectiles();
                for (int i = 0; i < projectiles.size(); i++) {
                    Projectile p = (Projectile) projectiles.get(i);
                    if (p.isVisible() == true) {
                        p.update();
                    } else {
                        projectiles.remove(i);
                    }
                }

                if ( roomchange == true)
                {

                    maparray.clear();
                    int x = 0;
                    enemyarray.clear();
                    Enemy g= new Goblin (10,10,5,0,0, r.nextInt((680 - 80) + 1) + 80, r.nextInt((400 - 80) + 1) + 80,1);
                    enemyarray.add(g);

                    for ( x = 1; x <= 15; x++)
                    {
                        String are = "data/rooms/" + x +".txt";
                        try {
                            loadMap(are,x);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    roomchange = false;
                }
                camera.update();
                minimapplayer.update();
                player.update();
                updateMap();
                updateEnemy();
                repaint();
                try {
                    Thread.sleep(17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (player.getHealth() <= 0) {
                    state = GameState.Dead;
                }
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {

        if (state == GameState.Running) {
            paintMap(g);
            paintEnemy(g);
            ArrayList projectiles = player.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                g.setColor(Color.YELLOW);
                g.fillRect(p.getX() - camera.getCenterX(), p.getY() - camera.getCenterY(), 10, 5);
            }

            g.drawRect((int)player.Top.getX() - camera.getCenterX() + 25, (int)player.Top.getY() - camera.getCenterY() + 25, (int)player.Top.getWidth(), (int)player.Top.getHeight());
            g.drawRect((int)player.Bottom.getX() - camera.getCenterX()  + 25, (int)player.Bottom.getY() - camera.getCenterY() + 25, (int)player.Bottom.getWidth(), (int)player.Bottom.getHeight());
            g.drawRect((int)player.Left.getX() - camera.getCenterX() + 25, (int)player.Left.getY() - camera.getCenterY() + 25, (int)player.Left.getWidth(), (int)player.Left.getHeight());
            g.drawRect((int)player.Right.getX() - camera.getCenterX() + 25, (int)player.Right.getY() - camera.getCenterY() + 25, (int)player.Right.getWidth(), (int)player.Right.getHeight());
            g.drawRect((int)player.CollisionZone.getX() - camera.getCenterX() + 25, (int)player.CollisionZone.getY() - camera.getCenterY() + 25, (int)player.CollisionZone.getWidth(), (int)player.CollisionZone.getHeight());
            g.drawImage(character, player.getCenterX() - camera.getCenterX(), player.getCenterY() - camera.getCenterY(), this);

            if(minimapOn == true){
                g.drawImage(mapplayer, minimapplayer.getCenterX() - 5, minimapplayer.getCenterY() - 5, this);
            }
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(area), 740, 30);  

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(score), 40, 30);  

        } else if (state == GameState.Dead) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 480);
            g.setColor(Color.WHITE);
            g.drawString("Press Space To Restart", 160, 240);

        }
    }

    private void updateMap() {
        for (int i = 0; i < maparray.size(); i++) {
            Map e = (Map) maparray.get(i);
            e.update();
        }
        for (int i = 0; i < minimaparray.size(); i++) {
            Map e = (Map) minimaparray.get(i);
            e.update();
        }
    }

    private void paintMap(Graphics g) {
        for (int i = 0; i < maparray.size(); i++) {
            Map e = (Map) maparray.get(i);
            g.drawImage(e.getImage(), e.getCenterX() + 25 - camera.getCenterX() , e.getCenterY() + 25 - camera.getCenterY() , this);
        }
        for (int i = 0; i < minimaparray.size(); i++) {
            Map e = (Map) minimaparray.get(i);
            g.drawImage(e.getImage(), e.getCenterX() + 50 , e.getCenterY() + 50 , this);
        }
    }

    private void updateEnemy(){
        for (int i = 0; i < enemyarray.size(); i++) {
            Enemy e = (Enemy) enemyarray.get(i);
            e.update();
        }
    }

    private void paintEnemy(Graphics g) {
        for (int i = 0; i < enemyarray.size(); i++) {
            Enemy e = (Enemy) enemyarray.get(i);
            g.drawImage(e.getImage(), e.getCenterX()  - camera.getCenterX()  , e.getCenterY()  - camera.getCenterY()  , this);
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {

        switch (k.getKeyCode()) {
            case KeyEvent.VK_W:
            camera.moveUp();
            camera.setMovingUp(true);
            player.moveUp();
            player.setMovingUp(true);
            break;

            case KeyEvent.VK_S:
            camera.moveDown();
            camera.setMovingDown(true);
            player.moveDown();
            player.setMovingDown(true);
            break;

            case KeyEvent.VK_A:
            camera.moveLeft();
            camera.setMovingLeft(true);
            player.moveLeft();
            player.setMovingLeft(true);
            break;

            case KeyEvent.VK_D:
            camera.moveRight();
            camera.setMovingRight(true);
            player.moveRight();
            player.setMovingRight(true);
            break;

            case KeyEvent.VK_CONTROL:
            if (player.isReadyToFire()) {
                player.shoot();
                player.setReadyToFire(false);
            }
            break;

            case KeyEvent.VK_SPACE:
            if (state == GameState.Dead) {
                reset();
            }
            else{
                for ( int x = 1; x <= 15; x++)
                {
                    String are = "data/rooms/minimap/" + x +".txt";
                    try {
                        loadMiniMap(are,x);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                minimapOn = true;
            }
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            camera.stopUp();
            player.stopUp();
            break;

            case KeyEvent.VK_S:
            camera.stopDown();
            player.stopDown();
            break;

            case KeyEvent.VK_A:
            camera.stopLeft();
            player.stopLeft();
            break;

            case KeyEvent.VK_D:
            camera.stopRight();
            player.stopRight();
            break;

            case KeyEvent.VK_CONTROL:
            player.setReadyToFire(true);
            break;

            case KeyEvent.VK_SPACE:
            minimaparray.clear(); 
            minimapOn = false;
            break;

        }

    }

    public void reset(){
        maparray.clear();
        minimaparray.clear();
        enemyarray.clear();
        player.health += 10;
        player.speedX = 0;
        player.speedY = 0;
        Map.x = 0;
        Map.y = 0;
        score = 0;
        minimapOn = false;
        roomchange = true ;
        start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public static Player getplayer() {
        return player;   
    }

    public static Minimapplayer getminimapplayer() {
        return minimapplayer;   
    }

    public static Camera getcamera() {
        return camera;   
    }

}