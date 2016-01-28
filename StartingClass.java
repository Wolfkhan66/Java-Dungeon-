
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {

    private static Player player;
    public static Image image, character, tile, floor, door1, door2;
    private Graphics second;
    private URL base;
    private ArrayList<Map> maparray = new ArrayList<Map>();
    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Grid Alpha");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Image Setups
        character = getImage(base, "data/character2.png");
        tile = getImage(base, "data/tile3.png");
        floor = getImage(base, "data/floor2.png");
        door1 = getImage(base, "data/Door1.png");
                door2 = getImage(base, "data/Door2.png");
    }

    @Override
    public void start() {
        player = new Player();
        Thread thread = new Thread(this);
        thread.start();

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

        int x = 0;
        int y = 0;

        Map g = new Tile (20,20, 1);
        maparray.add(g);
        g = new Tile (20,460, 1);
        maparray.add(g);
        g = new Tile (780,460, 1);
        maparray.add(g);
        g = new Tile (780,20, 1);
        maparray.add(g);
        
        g = new Door ( 20,180,1);
        maparray.add(g);
                g = new Door ( 780,180,2);
        maparray.add(g);
                g = new Door ( 340,20,3);
        maparray.add(g);
                g = new Door ( 340,460,4);
        maparray.add(g);
        

        for ( x = 0 ; x <= 6 ; x ++ )
        {
            g = new Tile (60 + ( x * 40),20, 1);
            maparray.add(g);
            g = new Tile (500 + ( x * 40),20, 1);
            maparray.add(g);
            g = new Tile (60 + ( x * 40),460, 1);
            maparray.add(g);
            g = new Tile (500 + ( x * 40),460, 1);
            maparray.add(g);
        }
        
                for ( x = 0 ; x <= 2 ; x ++ )
        {
            g = new Tile (20 ,60 + ( x * 40), 1);
            maparray.add(g);
            g = new Tile (20,340 + ( x * 40), 1);
            maparray.add(g);
            g = new Tile (780,60 + ( x * 40), 1);
            maparray.add(g);
            g = new Tile (780,340 + ( x * 40), 1);
            maparray.add(g);
        }
        

        /*
        // fill in the walls for each room type.
        // As room type 15 only has corners and no walls, it is not included as it
        // has already been generated above.
        if( map[x][y] == 1)
        {
        }
        if( map[x][y] == 2)
        {
        }
        if( map[x][y] == 3)
        {
        }
        if( map[x][y] == 4)
        {
        }
        if( map[x][y] == 5)
        {
        }
        if( map[x][y] == 6)
        {
        }
        if( map[x][y] == 7)
        {
        }
        if( map[x][y] == 8)
        {
        }
        if( map[x][y] == 9)
        {
        }
        if( map[x][y] == 10)
        {
        }
        if( map[x][y] == 11)
        {
        }
        if( map[x][y] == 12)
        {
        }
        if( map[x][y] == 13)
        {
        }
        if( map[x][y] == 14)
        {
        }
         */
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
            player.update();
            
            updateMap();
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

        g.drawRect((int)player.Top.getX(), (int)player.Top.getY(), (int)player.Top.getWidth(), (int)player.Top.getHeight());
        g.drawRect((int)player.Bottom.getX(), (int)player.Bottom.getY(), (int)player.Bottom.getWidth(), (int)player.Bottom.getHeight());
        g.drawRect((int)player.Left.getX(), (int)player.Left.getY(), (int)player.Left.getWidth(), (int)player.Left.getHeight());
        g.drawRect((int)player.Right.getX(), (int)player.Right.getY(), (int)player.Right.getWidth(), (int)player.Right.getHeight());
        g.drawRect((int)player.CollisionZone.getX(), (int)player.CollisionZone.getY(), (int)player.CollisionZone.getWidth(), (int)player.CollisionZone.getHeight());
        g.drawImage(character, player.getCenterX() - 25, player.getCenterY() - 25, this);

    }

    private void updateMap() {

        for (int i = 0; i < maparray.size(); i++) {
            Map e = (Map) maparray.get(i);
            e.update();
        }
    }

    private void paintMap(Graphics g) {
        for (int i = 0; i < maparray.size(); i++) {
            Map e = (Map) maparray.get(i);
            g.drawImage(e.getImage(), e.getCenterX() -20 , e.getCenterY() -20 , this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

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
            System.out.println("");
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