
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

    private static Player player;
    public static Image image, character, tile, minimap, door1, door2, goblin;
    private Graphics second;
    private URL base;

    private ArrayList<Map> maparray = new ArrayList<Map>();
    private ArrayList<Map> roomarray = new ArrayList<Map>();
    private ArrayList<Map> minimaparray = new ArrayList<Map>();
    private ArrayList<Enemy> enemyarray = new ArrayList<Enemy>();

    public static int area;
    public static boolean roomchange = true;

    private Font font = new Font(null, Font.BOLD, 30);
    Random r = new Random();

    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLUE);
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
        character = getImage(base, "data/character2.png");
        tile = getImage(base, "data/tile3.png");
        minimap = getImage(base, "data/tile.png");
        door1 = getImage(base, "data/Door1.png");
        door2 = getImage(base, "data/Door2.png");
        goblin = getImage(base, "data/tile2.png");
    }

    @Override
    public void start() {
        player = new Player();
        Thread thread = new Thread(this);
        thread.start();
        area = Map.area;
        int x = 0;
        int y = 0;

        Map g = new Tile (0,0, 1);
        maparray.add(g);
        g = new Tile (0,440, 1);
        maparray.add(g);
        g = new Tile (760,440, 1);
        maparray.add(g);
        g = new Tile (760,0, 1);
        maparray.add(g);

        g = new Door ( 0,160,2); // left
        maparray.add(g);
        g = new Door ( 760,160,3); // right
        maparray.add(g);
        g = new Door ( 320,0,4); // top
        maparray.add(g);
        g = new Door ( 320,440,5); // bottom
        maparray.add(g);

        for ( x = 0 ; x <= 6 ; x ++ )
        {
            g = new Tile (40 + ( x * 40),0, 1);
            maparray.add(g);
            g = new Tile (480 + ( x * 40),0, 1);
            maparray.add(g);
            g = new Tile (40 + ( x * 40),440, 1);
            maparray.add(g);
            g = new Tile (480 + ( x * 40),440, 1);
            maparray.add(g);
        }

        for ( x = 0 ; x <= 2 ; x ++ )
        {
            g = new Tile (0 ,40 + ( x * 40), 1);
            maparray.add(g);
            g = new Tile (0,320 + ( x * 40), 1);
            maparray.add(g);
            g = new Tile (760,40 + ( x * 40), 1);
            maparray.add(g);
            g = new Tile (760,320 + ( x * 40), 1);
            maparray.add(g);
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
        while (true) {
            if ( roomchange == true)
            {
                roomarray.clear();
                int x = 0;
                enemyarray.clear();
                Enemy e = new Goblin (10,10,5,0,0, r.nextInt((680 - 80) + 1) + 80, r.nextInt((400 - 80) + 1) + 80,1);
                enemyarray.add(e);

                for ( x = 0 ; x <= 3 ; x ++ )
                {   
                    if ( area == 1){
                        // right wall
                        Map g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // top wall
                        g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                        // bottom wall
                        g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 2){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // right wall
                        g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // bottom wall
                        g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 3){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // top wall
                        g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                        // bottom wall
                        g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 4){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // right wall
                        g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // top wall
                        g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 5){
                        // right wall
                        Map g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // top wall
                        g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 6){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // top wall
                        g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 7){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // bottom wall
                        g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 8){
                        // right wall
                        Map g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // bottom wall
                        g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 9){
                        // top wall
                        Map g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                        // bottom wall
                        g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 10){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                        // right wall
                        g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);  
                    }
                    else if ( area == 11){
                        // top wall
                        Map g = new Tile (320 + ( x * 40) , 0 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 12){
                        // left wall
                        Map g = new Tile (0,160 + ( x * 40), 1);
                        roomarray.add(g);
                    }
                    else if ( area == 13){
                        // bottom wall
                        Map g = new Tile (320  + ( x * 40) ,440 , 1);
                        roomarray.add(g);
                    }
                    else if ( area == 14){
                        // right wall
                        Map g = new Tile (760,160 + ( x * 40), 1);
                        roomarray.add(g);
                    }
                }
                roomchange = false;
            }
            player.update();
            updateMap();
            updateEnemy();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        paintMap(g);
        paintEnemy(g);

        g.drawRect((int)player.Top.getX(), (int)player.Top.getY(), (int)player.Top.getWidth(), (int)player.Top.getHeight());
        g.drawRect((int)player.Bottom.getX(), (int)player.Bottom.getY(), (int)player.Bottom.getWidth(), (int)player.Bottom.getHeight());
        g.drawRect((int)player.Left.getX(), (int)player.Left.getY(), (int)player.Left.getWidth(), (int)player.Left.getHeight());
        g.drawRect((int)player.Right.getX(), (int)player.Right.getY(), (int)player.Right.getWidth(), (int)player.Right.getHeight());
        g.drawRect((int)player.CollisionZone.getX(), (int)player.CollisionZone.getY(), (int)player.CollisionZone.getWidth(), (int)player.CollisionZone.getHeight());
        g.drawImage(character, player.getCenterX() - 25, player.getCenterY() - 25, this);

        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(area), 740, 30);  
    }

    private void updateMap() {
        for (int i = 0; i < maparray.size(); i++) {
            Map e = (Map) maparray.get(i);
            e.update();
        }
        for (int i = 0; i < roomarray.size(); i++) {
            Map e = (Map) roomarray.get(i);
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
        for (int i = 0; i < roomarray.size(); i++) {
            Map e = (Map) roomarray.get(i);
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

            case KeyEvent.VK_SPACE:

            for( int x = 0; x <= 9; x++)
            {
                for( int y = 0; y <= 9; y++)
                {
                    if ( map[x][y] == 1)
                    {

                        //above
                        Map g = new Tile (15 + (x * 30), 5  + ( y * 30) , 6 );
                        minimaparray.add(g);

                        //below
                        g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),6  );
                        minimaparray.add(g);

                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // top left
                        g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
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
                        Map g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),6  );
                        minimaparray.add(g);

                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),6 );
                        minimaparray.add(g);

                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // top left
                        g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),6  );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 3)
                    {
                        // top left
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),6  );
                        minimaparray.add(g);

                        //above
                        g = new Tile (15 + (x * 30), 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        //below
                        g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),6  );
                        minimaparray.add(g);

                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 4)
                    {
                        // top left
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);

                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,6 );
                        minimaparray.add(g);

                        //above
                        g = new Tile (15 + (x * 30), 5  + ( y * 30),6  );
                        minimaparray.add(g);

                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 5)
                    {

                        //above
                        Map g = new Tile (15 + (x * 30), 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,6 );
                        minimaparray.add(g);
                        // top left
                        g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) ,6);
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 6)
                    {
                        // top left
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),6  );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);

                        //above
                        g = new Tile (15 + (x * 30), 5  + ( y * 30) ,6 );
                        minimaparray.add(g);
                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 7)
                    {
                        // top left
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) ,6);
                        minimaparray.add(g);
                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),6 );
                        minimaparray.add(g);
                        //below
                        g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30) ,6 );
                        minimaparray.add(g);

                    }
                    if ( map[x][y] == 8)
                    {
                        // top left
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) ,6);
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);

                        //below
                        g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),6  );
                        minimaparray.add(g);
                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30),6  );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 9)
                    {
                        // top left
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) ,6);
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) ,6);
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);

                        //below
                        g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30),6  );
                        minimaparray.add(g);

                        //above
                        g = new Tile (15 + (x * 30), 5  + ( y * 30),6  );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 10)
                    {                    
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);
                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,6 );
                        minimaparray.add(g);
                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30),6 );
                        minimaparray.add(g);

                    }
                    if ( map[x][y] == 11)
                    {
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g);
                        //above
                        g = new Tile (15 + (x * 30), 5  + ( y * 30),6  );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 12)
                    {   
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) ,6);
                        minimaparray.add(g);
                        // left
                        g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) ,6);
                        minimaparray.add(g);

                    }
                    if ( map[x][y] == 13)
                    {
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),6  );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g); 

                        //below
                        g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30) ,6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 14)
                    {
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30),6  );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g); 

                        // right
                        g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30) ,6 );
                        minimaparray.add(g);
                    }
                    if ( map[x][y] == 15)
                    {
                        Map g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30) ,6 );
                        minimaparray.add(g);

                        // bottom right
                        g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // bottom left
                        g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30),6 );
                        minimaparray.add(g);

                        // top right
                        g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30),6 );
                        minimaparray.add(g); 
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

            case KeyEvent.VK_SPACE:
            minimaparray.clear(); 
            break;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public static Player getplayer() {
        return player;   
    }

}