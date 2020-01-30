import java.awt.Color;
public class SeamCarver{
    private Picture p;
    private int width;
    private int height;
    private double[][] energy;
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
        if(x == 0 || x == width() - 1 || y == 0  || y == height() - 1){
            return 1000;
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
    private double[][] transposeMatrix() {
        double [][] energyArr;
        energyArr = new double[width][height];
        for (int j = 0; j < width; j++) { 
            for (int i = 0; i < height; i++) {
                energyArr[j][i] = energy(j, i);
            }
        }
        return energyArr;
    }
    
    private double[][] energyMatrix() {
        double [][] energyArr;
        energyArr = new double[height][width];
        for (int j = 0; j < height; j++) { 
            for (int i = 0; i < width; i++) {
                energyArr[j][i] = energy(i, j);
            }
        }
        return energyArr;
    }
    
    private int[] seam(double[][] energy) {
        int height1 = energy.length;
        int width1 = energy[0].length;
        int[] path = new int[height1];
        if (width1 != 1) {
        int [][] Pos = new int [height1][width1];
        double [] rowEnergy1 = new double [width1];
        double [] rowEnergy2 = new double [width1];
        for (int k = 0; k < width1; k++) {
            rowEnergy1[k] = energy[0][k] + energy[1][k];
        }
        double min;
        int posi;
        for (int row = 2; row < height1-1; row++) {
            for (int col = 0; col < width1; col++) {
                if (col == 0) {
                    if(rowEnergy1[0] < rowEnergy1[1]){
                        min = rowEnergy1[0];
                    }
                    else{
                        min = rowEnergy1[1];

                    }
                    if(rowEnergy1[0] < rowEnergy1[1]){
                        posi = 0;
                    }
                    else{
                        posi = 1;
                    }
                    
                }
                else {
                    if(rowEnergy1[col-1] < rowEnergy1[col]){
                        min = rowEnergy1[col-1];
                    }
                    else{
                        min = rowEnergy1[col];
                    }
                    if(rowEnergy1[col-1] < rowEnergy1[col]){
                        posi = col-1;
                    }
                    else{
                        posi = col;
                    }
                    if (col != width1-1) {
                        if(min > rowEnergy1[col+1]){
                           
                            min = rowEnergy1[col+1];
                        }
                      
                        if (rowEnergy1[posi] > rowEnergy1[col+1]){
                            
                            posi = col+1;
                        }
                       
                    }
                }
                Pos[row-2][col] = posi;
                rowEnergy2[col] = energy[row][col] + min;
            }
            for (int enl = 0; enl < width1; enl++) {
                rowEnergy1[enl] = rowEnergy2[enl];
            }
        }
        int temPos = 0;
        for (int p = 1; p < width1-1; p++) {
            if(rowEnergy1[p] < rowEnergy1[temPos]) {
                temPos = p;
            }
        }
        if (temPos > 0)
            path[height1-1] = temPos-1;
        else 
            path[height1-1] = temPos;
        path[height1-2] = temPos;
        for (int m = height1-3; m > 0; m--) {
            path[m] = Pos[m-1][path[m+1]];
        }
        if (path[1] > 0)
            path[0] = path[1] - 1;
        else 
            path[0] = path[1];
        }
        return path;
    }
    public   int[] findVerticalSeam() {
        energy = energyMatrix();
        return seam(energy);
    }       
    
    public   int[] findHorizontalSeam() {
        energy = transposeMatrix();
        return seam(energy);
    }
    public void removeVerticalSeam(int[] rVS){
        if(rVS == null){
            throw new IllegalArgumentException();
        }

        Picture obj = new Picture(width - 1, height);
        Color color;
        int c;
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                if(rVS[j] == i){
                    continue;
                } 

                color = this.p.get(i,j);
                c = i;
                if(i > rVS[j]){
                    c--;
                } 
                obj.set(c,j,color);
            }
        }
        this.p = obj;
        this.width = this.p.width();
        this.height = this.p.height();
    }     
}