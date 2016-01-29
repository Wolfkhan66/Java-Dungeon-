
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

public class StartingClass extends Applet implements Runnable, KeyListener {

    private static Player player;
    public static Image image, character, tile, floor, door1, door2;
    private Graphics second;
    private URL base;
    private ArrayList<Map> maparray = new ArrayList<Map>();
    public static int area = 1;
    private Font font = new Font(null, Font.BOLD, 30);
    
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

        		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(area), 740, 30);	
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
            g.drawImage(e.getImage(), e.getCenterX() , e.getCenterY()  , this);
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