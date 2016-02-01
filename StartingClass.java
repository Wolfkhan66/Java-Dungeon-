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
        thread.start();
        area = Map.area;
    }

    private void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

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
        height = lines.size();

        for (int j = 0; j < 12; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {
                System.out.println(i + "is i ");

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Map t = new Tile(40 * i, 40 * j, Character.getNumericValue(ch));
                    maparray.add(t);
                    if( ch == 'a')
                    {
                        t = new Door ( 320,0,4); // top
                        maparray.add(t);
                    }
                    if( ch == 'b')
                    {
                        t = new Door ( 0,160,2); // left
                        maparray.add(t);
                    }
                    if( ch == 'c')
                    {
                        t = new Door ( 760,160,3); // right
                        maparray.add(t);
                    }
                    if( ch == 'd')
                    {
                        t = new Door ( 320,440,5); // bottom
                        maparray.add(t);
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
                        if (area == x)
                        {
                            String are = "data/rooms/" + x +".txt";
                            try {
                                loadMap(are);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }
                    roomchange = false;
                }

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
                g.fillRect(p.getX(), p.getY(), 10, 5);
            }

            //g.drawRect((int)player.Top.getX(), (int)player.Top.getY(), (int)player.Top.getWidth(), (int)player.Top.getHeight());
            //g.drawRect((int)player.Bottom.getX(), (int)player.Bottom.getY(), (int)player.Bottom.getWidth(), (int)player.Bottom.getHeight());
            //g.drawRect((int)player.Left.getX(), (int)player.Left.getY(), (int)player.Left.getWidth(), (int)player.Left.getHeight());
            //g.drawRect((int)player.Right.getX(), (int)player.Right.getY(), (int)player.Right.getWidth(), (int)player.Right.getHeight());
            //g.drawRect((int)player.CollisionZone.getX(), (int)player.CollisionZone.getY(), (int)player.CollisionZone.getWidth(), (int)player.CollisionZone.getHeight());
            g.drawImage(character, player.getCenterX() - 25, player.getCenterY() - 25, this);

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
            g.drawImage(e.getImage(), e.getCenterX() , e.getCenterY()  , this);
        }
        for (int i = 0; i < minimaparray.size(); i++) {
            Map e = (Map) minimaparray.get(i);
            g.drawImage(e.getImage(), e.getCenterX() , e.getCenterY()  , this);
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
            g.drawImage(e.getImage(), e.getCenterX() , e.getCenterY()  , this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            player.moveUp();
            player.setMovingUp(true);
            break;

            case KeyEvent.VK_S:
            player.moveDown();
            player.setMovingDown(true);
            break;

            case KeyEvent.VK_A:
            player.moveLeft();
            player.setMovingLeft(true);
            break;

            case KeyEvent.VK_D:
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
                minimapOn = true;
                for( int x = 0; x <= 9; x++)
                {
                    for( int y = 0; y <= 9; y++)
                    {
                        if ( map[x][y] == 1)
                        {

                            //above
                            Map g = new Tile (15 + (x * 30), 5  + ( y * 30) , 9 );
                            minimaparray.add(g);

                            //below
                            g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),9  );
                            minimaparray.add(g);

                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // top left
                            g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 2)
                        {
                            // center
                            //    Map g = new Tile (15 + (x * 30), 15 + ( y * 30)    );
                            //   minimaparray.add(g);

                            //above
                            //Map g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                            //minimaparray.add(g);

                            //below
                            Map g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),9  );
                            minimaparray.add(g);

                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),9 );
                            minimaparray.add(g);

                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // top left
                            g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),9  );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 3)
                        {
                            // top left
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),9  );
                            minimaparray.add(g);

                            //above
                            g = new Tile (15 + (x * 30), 5  + ( y * 30) ,9);
                            minimaparray.add(g);

                            //below
                            g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),9  );
                            minimaparray.add(g);

                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 4)
                        {
                            // top left
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);

                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,9 );
                            minimaparray.add(g);

                            //above
                            g = new Tile (15 + (x * 30), 5  + ( y * 30),9  );
                            minimaparray.add(g);

                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 5)
                        {

                            //above
                            Map g = new Tile (15 + (x * 30), 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,9 );
                            minimaparray.add(g);
                            // top left
                            g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) ,9);
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 6)
                        {
                            // top left
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),9  );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);

                            //above
                            g = new Tile (15 + (x * 30), 5  + ( y * 30) ,9 );
                            minimaparray.add(g);
                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 7)
                        {
                            // top left
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) ,9);
                            minimaparray.add(g);
                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),9 );
                            minimaparray.add(g);
                            //below
                            g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30) ,9 );
                            minimaparray.add(g);

                        }
                        if ( map[x][y] == 8)
                        {
                            // top left
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) ,9);
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);

                            //below
                            g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),9 );
                            minimaparray.add(g);
                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30),9  );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 9)
                        {
                            // top left
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) ,9);
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) ,9);
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);

                            //below
                            g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),9  );
                            minimaparray.add(g);

                            //above
                            g = new Tile (15 + (x * 30), 5  + ( y * 30),9  );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 10)
                        {                    
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);
                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,9 );
                            minimaparray.add(g);
                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),9 );
                            minimaparray.add(g);

                        }
                        if ( map[x][y] == 11)
                        {
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g);
                            //above
                            g = new Tile (15 + (x * 30), 5  + ( y * 30),9  );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 12)
                        {   
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) ,9);
                            minimaparray.add(g);
                            // left
                            g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) ,9);
                            minimaparray.add(g);

                        }
                        if ( map[x][y] == 13)
                        {
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g); 

                            //below
                            g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30) ,9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 14)
                        {
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),9  );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g); 

                            // right
                            g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,9 );
                            minimaparray.add(g);
                        }
                        if ( map[x][y] == 15)
                        {
                            Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,9 );
                            minimaparray.add(g);

                            // bottom right
                            g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // bottom left
                            g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),9 );
                            minimaparray.add(g);

                            // top right
                            g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),9 );
                            minimaparray.add(g); 
                        }
                    }
                }
            }

            break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            player.stopUp();
            break;

            case KeyEvent.VK_S:
            player.stopDown();
            break;

            case KeyEvent.VK_A:
            player.stopLeft();
            break;

            case KeyEvent.VK_D:
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

}