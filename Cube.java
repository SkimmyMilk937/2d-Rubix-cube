import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Cube {

    private Face[] faces;
    private Face[] horizontalFaces;
    private Face[] verticalFaces;
    private Face topFace;

    public Cube() {
        faces = new Face[6];
        horizontalFaces = new Face[4];
        verticalFaces = new Face[4];

        faces[0] = new Face(3, Color.orange);
        horizontalFaces[0] = faces[0];

        faces[1] = new Face(3, Color.yellow);
        verticalFaces[0] = faces[1];

        faces[2] = new Face(3, Color.blue);
        horizontalFaces[1] = faces[2];
        verticalFaces[1] = faces[2];
        topFace = faces[2];

        faces[3] = new Face(3, Color.white);
        verticalFaces[2] = faces[3];

        faces[4] = new Face(3, Color.red);
        horizontalFaces[2] = faces[4];

        faces[5] = new Face(3, Color.green);
        horizontalFaces[3] = faces[5];
        verticalFaces[3] = faces[5];

    }

    public void randomize() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int randomNum = rand.nextInt(6);
            switch (randomNum) {
                case 0:
                    rotateFaceClockwise(false);
                    break;
                case 1:
                    rotateFaceClockwise(true);
                    break;
                case 2:
                    rotateCol(true, true);
                    break;
                case 3:
                    rotateCol(false, true);
                    break;
                case 4:
                    rotateRow(true, true);
                    break;
                case 5:
                    rotateRow(false, true);
                    break;
            }
        }

    }

    public void rotateFaceClockwise(Boolean counterClockwise) {
        topFace.rotateFaceClockwise(counterClockwise);
        if(counterClockwise){
            rotateFaceCounterClockwiseTopRows();
            rotateFaceCounterClockwiseTopRows();
            rotateFaceCounterClockwiseTopRows();
        }
        else
            rotateFaceCounterClockwiseTopRows();
    }
    //HELPER
    public void rotateFaceCounterClockwiseTopRows() {
        Tile[] temp = Face.reverseTiles(faces[0].getCol(true));
        System.out.println("setting");
        faces[0].setCol(true, (faces[3].getRow(true)));
        faces[3].setRow(true, Face.reverseTiles(faces[4].getCol(false)));
        faces[4].setCol(false, faces[1].getRow(false));
        faces[1].setRow(false, (temp));
    }


    public void rotateCol(Boolean right, Boolean up) {

        topFace.rotateCol(right, up, verticalFaces);
        if(right){
            if(up)
                faces[4].rotateFaceClockwise(false);
            else
                faces[4].rotateFaceClockwise(true);
        }
        else{
            if(up)
                faces[0].rotateFaceClockwise(true);
            else
                faces[0].rotateFaceClockwise(false);
        }
    }

    public void rotateRow(Boolean top, Boolean right) {
        topFace.rotateRow(top, right, horizontalFaces);
        if(top){
            if(right)
                faces[1].rotateFaceClockwise(true);
            else
                faces[1].rotateFaceClockwise(false);
        }
        else{
            if(right)
                faces[3].rotateFaceClockwise(false );
            else
                faces[3].rotateFaceClockwise(true);
        }
    }

    public Face[] getFaces() {
        return faces;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Face face : faces) {
            sb.append(face.toString()).append("\n");
        }
        return sb.toString();
    }
}
