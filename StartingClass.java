
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

    private Player player;
    private Image image, character, tile;
    private Graphics second;
    private URL base;
    private ArrayList<Grid> gridlist = new ArrayList<Grid>();

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
        character = getImage(base, "data/character.png");
        tile = getImage(base, "data/tile.png");
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

        for( int x = 0; x <= 9; x++)
        {
            for( int y = 0; y <= 9; y++)
            {
                if ( map[x][y] == 1)
                {
                    // center
                    //    Grid g = new Tile (15 + (x * 30), 15 + ( y * 30)    );
                    //   gridlist.add(g);

                    //above
                    Grid g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);

                    //below
                    g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);

                    // left
                    //g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    //gridlist.add(g);

                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);

                    // top left
                    g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                }
                if ( map[x][y] == 2)
                {
                    // center
                    //    Grid g = new Tile (15 + (x * 30), 15 + ( y * 30)    );
                    //   gridlist.add(g);

                    //above
                    //Grid g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    //gridlist.add(g);

                    //below
                    Grid g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);

                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);

                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);

                    // top left
                    g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                }
                if ( map[x][y] == 3)
                {
                    // top left
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    //above
                    g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);

                    //below
                    g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);

                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                }
                if ( map[x][y] == 4)
                {
                    // top left
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);

                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);

                    //above
                    g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);

                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);
                }
                if ( map[x][y] == 5)
                {

                    //above
                    Grid g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);

                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);
                    // top left
                    g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                }
                if ( map[x][y] == 6)
                {
                    // top left
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);

                    //above
                    g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);
                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);
                }
                if ( map[x][y] == 7)
                {
                    // top left
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);
                    //below
                    g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);

                }
                if ( map[x][y] == 8)
                {
                    // top left
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);

                    //below
                    g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);
                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);
                }
                if ( map[x][y] == 9)
                {
                    // top left
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);

                    //below
                    g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);

                    //above
                    g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);
                }
                if ( map[x][y] == 10)
                {                    
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);
                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);

                }
                if ( map[x][y] == 11)
                {
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                    //above
                    g = new Tile (15 + (x * 30), 5  + ( y * 30)  );
                    gridlist.add(g);
                }
                if ( map[x][y] == 12)
                {   
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g);
                    // left
                    g = new Tile ( 5 + (x * 30 )    , 15   + ( y * 30) );
                    gridlist.add(g);

                }
                if ( map[x][y] == 13)
                {
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g); 

                    //below
                    g = new Tile ( 15 + (x * 30)   , 25  + ( y * 30)  );
                    gridlist.add(g);
                }
                if ( map[x][y] == 14)
                {
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g); 

                    // right
                    g = new Tile ( 25 + (x * 30 ) , 15 + ( y * 30)  );
                    gridlist.add(g);
                }
                if ( map[x][y] == 15)
                {
                    Grid g = new Tile ( 5 + (x * 30 ) , 5  + ( y * 30)  );
                    gridlist.add(g);

                    // bottom right
                    g = new Tile ( 25 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // bottom left
                    g = new Tile ( 5 + (x * 30)  , 25  + ( y * 30) );
                    gridlist.add(g);

                    // top right
                    g = new Tile ( 25 + (x * 30 ), 5  + ( y * 30) );
                    gridlist.add(g); 
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
        while (true) {
            player.update();

            updateGrid();
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
        paintGrid(g);
        g.drawImage(character, player.getCenterX() - 5, player.getCenterY() - 5, this);

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
            g.drawImage(tile, e.getCenterX() - 5, e.getCenterY() - 5, this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            player.setCenterY(player.getCenterY() - 10);
            break;

            case KeyEvent.VK_DOWN:
            player.setCenterY(player.getCenterY() + 10);
            break;

            case KeyEvent.VK_LEFT:
            player.setCenterX(player.getCenterX() - 10);
            break;

            case KeyEvent.VK_RIGHT:
            player.setCenterX(player.getCenterX() + 10);
            break;

            case KeyEvent.VK_SPACE:

            break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            player.stop();
            break;

            case KeyEvent.VK_DOWN:
            player.stop();
            break;

            case KeyEvent.VK_LEFT:
            player.stop();
            break;

            case KeyEvent.VK_RIGHT:
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

}