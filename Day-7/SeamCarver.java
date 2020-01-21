import java.awt.Color;
public class SeamCarver{
    private Picture p;
    private int width;
    private int height;
    public SeamCarver(Picture picture){
        p = new Picture(picture);
        width = picture.width();
        height = picture.height();
    }
    public Picture picture(){
        return p;
    }
    public int width(){
        return width;

    }
    public int height(){
        return height;
    }


    public double energy(int x, int y) {
        if(x < 0 || x >width() -1 || y < 0 || y > height() - 1){
            throw new IndexOutOfBoundsException();
        }
        if(x == 0 || y == 0 || x == width() - 1 || y == height() - 1){
            return 0;
        }

        double deltax = 0.0;
        double deltay = 0.0;
        Color x1,y1,x2,y2;
        x1 = p.get(x+1,y);
        x2 = p.get(x-1,y);
        y1 = p.get(x,y+1);
        y2 = p.get(x,y-1);

        double abR = x1.getRed() - x2.getRed();
        double abG = x1.getGreen() - x2.getGreen();
        double abB = x1.getBlue() - x2.getBlue();

        double cdR = y1.getRed() - y2.getRed();
        double cdG = y1.getGreen() - y2.getGreen();
        double cdB = y1.getBlue() - y2.getBlue();

        deltax = (abR*abR) + (abG*abG) + (abB*abB);
        deltay = (cdR*cdR) + (cdG*cdG) + (cdB * cdB);
        double deltaenergy = Math.sqrt(deltax + deltay);

        return deltaenergy;


    }
}