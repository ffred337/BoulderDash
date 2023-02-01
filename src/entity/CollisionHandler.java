package entity;

import model.element.IElement;
import model.element.Map;
import model.element.mobile.Diamond;
import model.element.mobile.Enemy;
import model.element.mobile.Player;
import model.element.mobile.Rock;
import model.element.motionless.Bgd;

import java.io.IOException;

public class CollisionHandler {
    private Map map;

    public CollisionHandler(Map map) {
        this.map = map;
    }

    public void mechanicsManager() throws IOException {
       IElement[][] element = this.map.getMapElements();
       int mapHeight = this.map.getMapHeight();
       int mapWidth = this.map.getMapWidth();

       for(int j = mapHeight-1; j >=0; j--){
           for(int i = 0; i < mapWidth; i++){
               //If under a Rock and a Diamond we have an empty space
               if((element[i][j] instanceof Rock || element[i][j] instanceof Diamond) && element[i][j+1] instanceof Bgd){
                   //If it's a Rock we fall
                   if(element[i][j] instanceof Rock){
                       ((Rock) element[i][j]).setFallen(true);
                       ((Rock) element[i][j]).setFalling(((Rock) element[i][j]).getFalling()+1);
                       this.map.getMapElements()[i][j+1] = new Rock(i, j+1, this.map.getMapLc());
                       ((Rock) this.map.getMapElements()[i][j+1]).setFallen(true);
                   }
                   //Else it's a diamond we fall
                   else if (element[i][j] instanceof Diamond){
                       ((Diamond) element[i][j]).setFallen(true);
                       ((Diamond) element[i][j]).setFalling(((Diamond) element[i][j]).getFalling()+1);
                       this.map.getMapElements()[i][j+1] = new Diamond(i, j+1);
                   }
                   element[i][j] = new Bgd(i,j);
               }

                //If a diamond or a rock who was falling is on an enemy -> We kill him
               if((element[i][j] instanceof Rock || element[i][j] instanceof Diamond) && element[i][j+1] instanceof Player){
                   //If it's a Rock we fall
                   if(element[i][j] instanceof Rock){
                       if(((Rock)element[i][j]).isFallen()){
                           this.map.getPlayer().setAlive(false);
                           this.map.getPlayer().die();
                       }
                   }
                   //Else it's a diamond we fall
                   else if (element[i][j] instanceof Diamond){
                       if(((Diamond)element[i][j]).isFallen()){
                           this.map.getPlayer().setAlive(false);
                           this.map.getPlayer().die();//To remove
                       }

                   }
                   element[i][j] = new Bgd(i,j);
               }

               else if((element[i][j] instanceof Rock || element[i][j] instanceof Diamond) && element[i][j+1] instanceof Enemy){
                   if(element[i][j] instanceof Rock){
                       if(((Rock) element[i][j]).isFallen()){
                           this.map.getEnemies().remove((Enemy) element[i][j+1]);
                           this.map.getMapElements()[i][j+1] = new Bgd(i,j+1);
                            this.map.getMapElements()[i][j] = new Diamond(i,j);
                           this.map.getMapElements()[i+1][j] = new Diamond(i+1,j);
                           this.map.getMapElements()[i-1][j] = new Diamond(i-1, j);
                           this.map.getMapElements()[i][j+1] = new Diamond(i, j+1);
                           this.map.getMapElements()[i][j-1] = new Diamond(i, j-1);

                           this.map.getMapElements()[i-1][j-1] = new Diamond(i-1, j-1);
                           this.map.getMapElements()[i-1][j+1] = new Diamond(i-1, j+1);
                           this.map.getMapElements()[i+2][j-2] = new Diamond(i+2, j-2);
                           this.map.getMapElements()[i-1][j-1] = new Diamond(i-1, j-1);

                       }
                   }
                   else if (element[i][j] instanceof Diamond){

                   }
               }
               //If a rock is on another one and both have space on the left
               else if(element[i][j] instanceof Rock && element[i][j+1] instanceof Rock && element[i-1][j] instanceof Bgd && element[i-1][j-1] instanceof Bgd ){
                   this.map.getMapElements()[i-1][j] = new Rock(i-1,j,this.map.getMapLc());
                   ((Rock)this.map.getMapElements()[i-1][j]).setFallen(true);
                   this.map.getMapElements()[i][j] = new Bgd(i,j);
               }
               //If a rock is on another one and both have space on the right
               else if(element[i][j] instanceof Rock && element[i][j+1] instanceof Rock && element[i+1][j] instanceof Bgd && element[i+1][j-1] instanceof Bgd ){
                   this.map.getMapElements()[i+1][j] = new Rock(i+1,j,this.map.getMapLc());
                   ((Rock)this.map.getMapElements()[i+1][j]).setFallen(true);
                   this.map.getMapElements()[i][j] = new Bgd(i,j);
               }
               /*If a diamond is on another one and both have space on the left
               else if(element[i][j] instanceof Diamond && element[i][j+1] instanceof Diamond && element[i-1][j] instanceof Bgd && element[i-1][j-1] instanceof Bgd ){

               //If a diamond is on another one and both have space on the right
               */
               else{
                   //Else it's a rock we don't fall
                   if(element[i][j] instanceof Rock){
                       ((Rock) element[i][j]).setFallen(false);
                       ((Rock) element[i][j]).setFalling(0);
                   }
                   //Else it's a diamond we don't fall
                   else if (element[i][j] instanceof Diamond){
                       ((Diamond) element[i][j]).setFallen(false);
                       ((Diamond) element[i][j]).setFalling(0);
                   }
               }
           }
       }
       //this.map.update();
    }
}
