import java.util.ArrayList;
import java.awt.Color;

public class Face {

    private Tile[][] tiles; 

    public Face(int len, Color center) {

        this.tiles = new Tile[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.tiles[i][j] = new Tile(center);
            }
        }
    }

    public void rotateFaceClockwise(Boolean counterClockwise) {

        int len = tiles.length - 1;

        for (int i = 1; i < len + 1; i++) {
            Tile temp = tiles[0][i];
            tiles[0][i] = tiles[i][0];
            tiles[i][0] = temp;

            temp = tiles[len][i];
            tiles[len][i] = tiles[i][len];
            tiles[i][len] = temp;
        }

        for(int i = 0; i < 3; i++) {
            Tile temp = tiles[i][0];
            tiles[i][0] = tiles[i][2];
            tiles[i][2] = temp;
        }


        if(counterClockwise){
            rotateFaceClockwise(false);
            rotateFaceClockwise(false);
        }
    }


    public void rotateCol(Boolean right, Boolean up, Face[] faces) {

        Tile[] temp = faces[1].getCol(right);
        Tile[] temp2 = faces[2].getCol(right);

        faces[2].setCol(right, temp);
        temp = faces[3].getCol(right);
        faces[3].setCol(right, reverseTiles(temp2));
        temp2 = faces[0].getCol(right);
        faces[0].setCol(right, reverseTiles(temp));
        faces[1].setCol(right, temp2);

        if(up){
            rotateCol(right, false, faces);
            rotateCol(right, false, faces);
        }

        
    }

    public static Tile[] reverseTiles(Tile[] tiles) {
        Tile[] temp = new Tile[3];
        temp[0] = tiles[2];
        temp[1] = tiles[1];
        temp[2] = tiles[0];

        return temp;
    }


    public void rotateRow(Boolean top, Boolean right, Face[] faces) {

        Tile[] temp = faces[1].getRow(top);
        Tile[] temp2 = faces[2].getRow(top);



        faces[2].setRow(top, temp);
        temp = faces[3].getRow(top);
        faces[3].setRow(top, reverseTiles(temp2));
        temp2 = faces[0].getRow(top);
        faces[0].setRow(top, reverseTiles(temp));
        faces[1].setRow(top, temp2);


        if(!right){
            rotateRow(top, true, faces);
            rotateRow(top, true, faces);
        }



    }

    public Tile[] getCol(Boolean right) {
        int num = right ? tiles.length - 1 : 0;

        Tile[] col = new Tile[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            col[i] = tiles[i][num]; // Assuming you want the first column
        }
        return col;
    }

    public void setCol(Boolean right, Tile[] col) {
        int num = right ? tiles.length - 1 : 0;

        for (int i = 0; i < tiles.length; i++) {
            tiles[i][num] = col[i]; // Assuming you want the first column
        }
    }

    public Tile[] getRow(Boolean top) {
        int num = top ? 0 : tiles.length - 1;

        Tile[] row = new Tile[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            row[i] = tiles[num][i]; // Assuming you want the first row
        }
        return row;
    }

    public void setRow(Boolean top, Tile[] row) {
        int num = top ? 0 : tiles.length - 1;

        for (int i = 0; i < tiles.length; i++) {
            tiles[num][i] = row[i]; // Assuming you want the first row
        }
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                sb.append(tiles[i][j].toString()).append(" ");
            }

                sb.append("\n");
        }
        return sb.toString();
    }

}
