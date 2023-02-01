package model.element;

import model.element.mobile.Diamond;
import model.element.mobile.Enemy;
import model.element.mobile.Player;
import model.element.mobile.Rock;
import model.element.motionless.Bgd;
import model.element.motionless.Dirt;
import model.element.motionless.Exit;
import model.element.motionless.Wall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class Map extends Observable {
    private int id;
    private final int mapWidth = 30;
    private final int mapHeight = 16;
    private String content;
    private IElement[][] mapElements;
    private int diamondCounter;
    private long StartTime;
    private LevelController mapLc;
    private boolean haveWon;

    public LevelController getMapLc() {
        return mapLc;
    }

    public void setMapLc(LevelController mapLc) {
        this.mapLc = mapLc;
    }
    public Map(final int id, final String content, LevelController lc) throws IOException {
        this.setId(id);
        this.setContent(content);
        this.diamondCounter = 0;
        this.setStartTime(System.currentTimeMillis());
        this.mapLc = lc;
        this.mapElements = new IElement[this.getMapWidth()][this.getMapHeight()];
        createMap();
        this.haveWon = false;
    }
    public Map(){}


    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {return this.content; }
    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setStartTime(long startTime) {
        StartTime = startTime;
    }

    public void createMap() throws IOException {
        String content = this.getContent();
        String level = content.replaceAll(" ", "");
        for(int j = 0; j < this.getMapHeight(); j++){
            String[] rows = level.split("\n");
            for(int i = 0; i < this.getMapWidth(); i++){
                //String[] col = rows[j].strip();
                    switch (rows[j].toCharArray()[i]){
                        case 'G':
                            this.mapElements[i][j] = new Bgd(i,j);
                            break;
                        case 'T':
                            this.mapElements[i][j] = new Dirt(i, j, this.getMapLc());
                            break;
                        case 'W':
                            this.mapElements[i][j] = new Wall(i, j ,this.getMapLc());
                            break;
                        case 'X':
                            this.mapElements[i][j] = new Exit(i,j);
                            break;
                        case 'D':
                            this.mapElements[i][j] = new Diamond(i,j);
                            break;
                        case 'R':
                            this.mapElements[i][j] = new Rock(i,j, this.getMapLc());
                            break;
                        case 'E':
                            this.mapElements[i][j] = new Enemy(i,j,this.getMapLc());
                            break;
                        case 'P':
                            this.mapElements[i][j] = new Player(i,j);
                            break;
                        default:
                            break;

                    }
            }
        }
    }
    public IElement[][] getMapElements(){return  this.mapElements;}
    public Player getPlayer(){
        IElement[][] elements = this.getMapElements();
        for(int j = 0; j < getMapHeight(); j++){
            for(int i =0; i < getMapWidth(); i++){
                if(elements[i][j] instanceof Player){
                    return (Player) elements[i][j];
                }
            }
        }
        return null;
    }

    public boolean isPlayerAlive(){
        return getPlayer().getAlive();
    }
    public int getDiamondCounter() {
        return diamondCounter;
    }

    public void setDiamondCounter(int diamondCounter) {
        this.diamondCounter = diamondCounter;
    }
    public ArrayList<Diamond> getDiamonds(){
        IElement[][] elements = this.getMapElements();
        ArrayList<Diamond> diamonds = new ArrayList<>();
        for(int j =0; j < getMapHeight(); j++){
            for(int i =0; i < getMapWidth(); i++){
                if(elements[i][j] instanceof Diamond){
                    diamonds.add((Diamond) elements[i][j]);
                }
            }
        }
        return diamonds;
    }
    public ArrayList<Enemy> getEnemies(){
        IElement[][] elements = this.getMapElements();
        ArrayList<Enemy> enemies = new ArrayList<>();
        for(int j =0; j < getMapHeight(); j++){
            for(int i =0; i < getMapWidth(); i++){
                if(elements[i][j] instanceof Enemy){
                    enemies.add((Enemy) elements[i][j]);
                }
            }
        }
        return enemies;
    }
    private void updateDiamonds(){
        ArrayList<Diamond> diamonds = getDiamonds();
        for(Diamond diamond : diamonds){
            diamond.update();
        }
    }
    private void updateEnemies(){
        ArrayList<Enemy> enemies = getEnemies();
        for(Enemy enemy : enemies){
            enemy.update();
        }
    }
    public void update(){
        updateDiamonds();
        updateEnemies();
    }

    private boolean onCollisionEnter(IElement[][] playerPoint, int x, int y){
        if(playerPoint[x][y] instanceof Rock || playerPoint[x][y] instanceof Wall || playerPoint[x][y] instanceof Enemy || playerPoint[x][y] instanceof Diamond || playerPoint[x][y] instanceof Exit){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isRock(IElement[][] playerPoint, int x, int y){
        if(playerPoint[x][y] instanceof Rock){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isEnemy(IElement[][] playerPoint, int x, int y){
        if(playerPoint[x][y] instanceof Enemy){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isDiamond(IElement[][] playerPoint, int x, int y){
        if(playerPoint[x][y] instanceof Diamond){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean canWin(IElement[][] playerPoint, int x, int y){
        if(playerPoint[x][y] instanceof Exit && getDiamondCounter() >= 10){
            this.haveWon = true;
            return true;
        }
        else{
            this.haveWon = false;
            return false;
        }
    }

    public void movePlayerUp() throws IOException {
        int x = getPlayer().getX();
        int y = getPlayer().getY();
        boolean onCollision = onCollisionEnter(getMapElements(),x, y-1);
        boolean isDiamond = isDiamond(getMapElements(),x, y-1);
        boolean isEnemy = isEnemy(getMapElements(),x, y-1);
        boolean canWin = canWin(getMapElements(),x, y-1);
        if(!onCollision){
            //We can move
            getMapElements()[x][y-1] = new Player(x,y-1);
            getPlayer().moveUp();
            getMapElements()[x][y] = new Bgd(x,y);
        }
        else if(isDiamond){
            //We can move and collect
            getMapElements()[x][y-1] = new Player(x,y-1);
            getPlayer().moveUp();
            getMapElements()[x][y] = new Bgd(x,y);
            this.diamondCounter ++;
        }
        else if(isEnemy){
            //We can move and die
            getMapElements()[x][y-1] = new Player(x,y-1);
            getPlayer().moveUp();
            getMapElements()[x][y] = new Bgd(x,y);
            getPlayer().setAlive(false);
            getPlayer().die();
        }
        else if (canWin){
            //We can move and win
            getMapElements()[x][y-1] = new Player(x,y-1);
            getPlayer().moveUp();
            getMapElements()[x][y] = new Bgd(x,y);
        }

    }

    public void movePlayerDown() throws IOException {
        int x = getPlayer().getX();
        int y = getPlayer().getY();
        boolean onCollision = onCollisionEnter(getMapElements(),x, y+1);
        boolean isDiamond = isDiamond(getMapElements(),x, y+1);
        boolean isEnemy = isEnemy(getMapElements(),x, y+1);
        boolean canWin = canWin(getMapElements(),x, y+1);
        if(!onCollision){
            //We can move
            getMapElements()[x][y+1] = new Player(x,y+1);
            getPlayer().moveDown();
            getMapElements()[x][y] = new Bgd(x,y);
        }
        else if(isDiamond){
            //We can move and collect
            getMapElements()[x][y+1] = new Player(x,y+1);
            getPlayer().moveDown();
            getMapElements()[x][y] = new Bgd(x,y);
            this.diamondCounter ++;
        }
        else if(isEnemy){
            //We can move and die
            getMapElements()[x][y+1] = new Player(x,y+1);
            getPlayer().moveDown();
            getMapElements()[x][y] = new Bgd(x,y);
            getPlayer().setAlive(false);
            getPlayer().die();
        }
        else if (canWin){
            //We can move and win
            getMapElements()[x][y+1] = new Player(x,y+1);
            getPlayer().moveDown();
            getMapElements()[x][y] = new Bgd(x,y);
        }

    }

    public void movePlayerRight() throws IOException {
        int x = getPlayer().getX();
        int y = getPlayer().getY();
        boolean onCollision = onCollisionEnter(getMapElements(),x+1, y);
        boolean isRock = isRock(getMapElements(),x+1, y);
        boolean isDiamond = isDiamond(getMapElements(),x+1, y);
        boolean isEnemy = isEnemy(getMapElements(),x+1, y);
        boolean canWin = canWin(getMapElements(),x+1, y);
        if(!onCollision){
            //We can move
            getMapElements()[x+1][y] = new Player(x+1,y);
            getPlayer().moveRight();
            getMapElements()[x][y] = new Bgd(x,y);
        }
        else if(isRock){
            if(!onCollisionEnter(getMapElements(),x+2, y)){
                getMapElements()[x+2][y] = new Rock(x+2,y,this.getMapLc());
                getMapElements()[x+1][y] = new Player(x+1,y);
                getPlayer().moveRight();
                getMapElements()[x][y] = new Bgd(x,y);

            }

        }
        else if(isDiamond){
            //We can move and collect
            getMapElements()[x+1][y] = new Player(x+1,y);
            getPlayer().moveRight();
            getMapElements()[x][y] = new Bgd(x,y);
            this.diamondCounter ++;
        }
        else if(isEnemy){
            //We can move and die
            getMapElements()[x+1][y] = new Player(x+1,y);
            getPlayer().moveRight();
            getMapElements()[x][y] = new Bgd(x,y);
            getPlayer().setAlive(false);
            getPlayer().die();
        }
        else if (canWin){
            //We can move and win
            getMapElements()[x+1][y] = new Player(x+1,y);
            getPlayer().moveRight();
            getMapElements()[x][y] = new Bgd(x,y);
        }

    }

    public void movePlayerLeft() throws IOException {
        int x = getPlayer().getX();
        int y = getPlayer().getY();
        boolean onCollision = onCollisionEnter(getMapElements(),x-1, y);
        boolean isRock = isRock(getMapElements(),x-1, y);
        boolean isDiamond = isDiamond(getMapElements(),x-1, y);
        boolean isEnemy = isEnemy(getMapElements(),x-1, y);
        boolean canWin = canWin(getMapElements(),x-1, y);
        if(!onCollision){
            //We can move
            getMapElements()[x-1][y] = new Player(x-1,y);
            getPlayer().moveLeft();
            getMapElements()[x][y] = new Bgd(x,y);
        }
        else if(isRock){
            if(!onCollisionEnter(getMapElements(),x-2, y)){
                getMapElements()[x-2][y] = new Rock(x-2,y,this.getMapLc());
                getMapElements()[x-1][y] = new Player(x-1,y);
                getPlayer().moveLeft();
                getMapElements()[x][y] = new Bgd(x,y);

            }

        }
        else if(isDiamond){
            //We can move and collect
            getMapElements()[x-1][y] = new Player(x-1,y);
            getPlayer().moveLeft();
            getMapElements()[x][y] = new Bgd(x,y);
            this.diamondCounter ++;
        }
        else if(isEnemy){
            //We can move and die
            getMapElements()[x-1][y] = new Player(x-1,y);
            getPlayer().moveLeft();
            getMapElements()[x][y] = new Bgd(x,y);
            getPlayer().setAlive(false);
            getPlayer().die();
        }
        else if (canWin){
            //We can move and win
            getMapElements()[x-1][y] = new Player(x-1,y);
            getPlayer().moveLeft();
            getMapElements()[x][y] = new Bgd(x,y);
        }

    }
    public boolean getHaveWon(){return this.haveWon;}



}
