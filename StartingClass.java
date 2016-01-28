
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
    private Image image, character, tile, floor;
    private Graphics second;
    private URL base;
    private ArrayList<Grid> gridlist = new ArrayList<Grid>();
    private ArrayList<Floor> floorlist = new ArrayList<Floor>();
    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLACK);
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
        tile = getImage(base, "data/tile2.png");
        floor = getImage(base, "data/floor2.png");
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

        // Generate corners for all the rooms.
        for (x = 0; x <= 9 ; x++)
        {
            for (y = 0; y <= 9 ; y++)
            {
                Floor f = new Floor (225 + (x * 450), 225  + ( y * 450)  );
                floorlist.add(f);
                Grid g = new Tile (25 + (x * 450), 25  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (25 + (x * 450), 75  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (25 + (x * 450), 125  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (25 + (x * 450), 325  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (25 + (x * 450), 375  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (25 + (x * 450), 425  + ( y * 450)  );
                gridlist.add(g);
                ////////////////////////////////////////////////
                ////////////////////////////////////////////////
                g = new Tile (75 + (x * 450), 25  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (125 + (x * 450), 25  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (325 + (x * 450), 25  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (375 + (x * 450), 25  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (425 + (x * 450), 25  + ( y * 450)  );
                gridlist.add(g);
                ////////////////////////////////////////////////
                ////////////////////////////////////////////////
                g = new Tile (425 + (x * 450), 75  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (425 + (x * 450), 125  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (425 + (x * 450), 325  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (425 + (x * 450), 375  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (425 + (x * 450), 425  + ( y * 450)  );
                gridlist.add(g);
                ////////////////////////////////////////////////
                ////////////////////////////////////////////////
                g = new Tile (75 + (x * 450), 425  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (125 + (x * 450), 425  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (325 + (x * 450), 425  + ( y * 450)  );
                gridlist.add(g);
                g = new Tile (375 + (x * 450), 425  + ( y * 450)  );
                gridlist.add(g);
            }      
        }

        for (x = 0; x <= 9 ; x++)
        {
            for (y = 0; y <= 9 ; y++)
            {
                // fill in the walls for each room type.
                // As room type 15 only has corners and no walls, it is not included as it
                // has already been generated above.
                if( map[x][y] == 1)
                {
                    // Top Entrance
                    Grid g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);

                    // Right Entrance
                    g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Bottom Entrance
                    g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 2)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Right Entrance
                    g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Bottom Entrance
                    g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 3)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Top Entrance
                    g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);    

                    // Bottom Entrance
                    g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 4)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Top Entrance
                    g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);

                    // Right Entrance
                    g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 5)
                {

                    // Top Entrance
                    Grid g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);

                    // Right Entrance
                    g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 6)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Top Entrance
                    g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);

                }
                if( map[x][y] == 7)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Bottom Entrance
                    g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 8)
                {

                    // Right Entrance
                    Grid g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Bottom Entrance
                    g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 9)
                {

                    // Top Entrance
                    Grid g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);

                    // Bottom Entrance
                    g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 10)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);

                    // Right Entrance
                    g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 11)
                {

                    // Top Entrance
                    Grid g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
                    gridlist.add(g);

                }
                if( map[x][y] == 12)
                {
                    // left entrance
                    Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 13)
                {

                    // Bottom Entrance
                    Grid g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
                    gridlist.add(g);
                }
                if( map[x][y] == 14)
                {

                    // Right Entrance
                    Grid g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
                    gridlist.add(g);
                    g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
                    gridlist.add(g);
                }
            }
        }

        /* the entrances for each room. used to fill in sides of a room that are walls.
        ////////////////////////////////////////////////
        ////////////////////////////////////////////////
        // left entrance
        Grid g = new Tile (25 + (x * 450), 175  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (25 + (x * 450), 225  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (25 + (x * 450), 275  + ( y * 450)  );
        gridlist.add(g);

        // Top Entrance
        g = new Tile (175 + (x * 450), 25  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (225 + (x * 450), 25  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (275 + (x * 450), 25  + ( y * 450)  );
        gridlist.add(g);

        // Right Entrance
        g = new Tile (425 + (x * 450), 175  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (425 + (x * 450), 225  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (425 + (x * 450), 275  + ( y * 450)  );
        gridlist.add(g);

        // Bottom Entrance
        g = new Tile (175 + (x * 450), 425  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (225 + (x * 450), 425  + ( y * 450)  );
        gridlist.add(g);
        g = new Tile (275 + (x * 450), 425  + ( y * 450)  );
        gridlist.add(g);
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

            updateGrid();
            updateFloor();
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
        paintFloor(g);
        paintGrid(g);

        g.drawRect((int)player.Top.getX(), (int)player.Top.getY(), (int)player.Top.getWidth(), (int)player.Top.getHeight());
         g.drawRect((int)player.Bottom.getX(), (int)player.Bottom.getY(), (int)player.Bottom.getWidth(), (int)player.Bottom.getHeight());
          g.drawRect((int)player.Left.getX(), (int)player.Left.getY(), (int)player.Left.getWidth(), (int)player.Left.getHeight());
           g.drawRect((int)player.Right.getX(), (int)player.Right.getY(), (int)player.Right.getWidth(), (int)player.Right.getHeight());
            g.drawRect((int)player.CollisionZone.getX(), (int)player.CollisionZone.getY(), (int)player.CollisionZone.getWidth(), (int)player.CollisionZone.getHeight());
        g.drawImage(character, player.getCenterX() - 25, player.getCenterY() - 25, this);

    }

    private void updateGrid() {

        for (int i = 0; i < gridlist.size(); i++) {
            Grid e = (Grid) gridlist.get(i);
            e.update();
        }
    }

    private void paintGrid(Graphics g) {
        for (int i = 0; i < gridlist.size(); i++) {
            Grid e = (Grid) gridlist.get(i);
            g.drawImage(tile, e.getCenterX() - 25, e.getCenterY() - 25, this);
        }
    }

    private void updateFloor() {

        for (int i = 0; i < floorlist.size(); i++) {
            Floor e = (Floor) floorlist.get(i);
            e.update();
        }
    }

    private void paintFloor(Graphics g) {
        for (int i = 0; i < floorlist.size(); i++) {
            Floor e = (Floor) floorlist.get(i);
            g.drawImage(floor, e.getCenterX() - 175, e.getCenterY() - 175, this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            player.moveUp();
            break;

            case KeyEvent.VK_S:
            player.moveDown();
            break;

            case KeyEvent.VK_A:
            player.moveLeft();
            break;

            case KeyEvent.VK_D:
            player.moveRight();
            break;

            case KeyEvent.VK_SPACE:

            break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            player.stop();
            break;

            case KeyEvent.VK_S:
            player.stop();
            break;

            case KeyEvent.VK_A:
            player.stop();
            break;

            case KeyEvent.VK_D:
            player.stop();
            break;

            case KeyEvent.VK_SPACE:
            System.out.println("Stop jumping");
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