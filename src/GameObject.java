public class GameObject {
    private int size;
    private String gamePiece;
    public GameObject(int size, String gamePiece){
        this.size =size;
        this.gamePiece = gamePiece;
    }
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size = size;
    }
    public String getGamePiece(){
        return gamePiece;
    }
    public void setGamePiece(String gamePiece){
        this.gamePiece = gamePiece;
    }
    public String toString(){
        return gamePiece;
    }
}
