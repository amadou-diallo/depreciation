
package business;

/**
 *
 * @author amadou
 */
public class AssetSL extends Asset {
    private double[] bbal, anndeprsl, ebal;
    private boolean built;
    
    public AssetSL() {
        super();
        this.built = false;
    }
    
    public AssetSL(String anm, double c, double s, int lf) {
        super(anm,c,s,lf);
        if (super.isValid()) {
            build();
        }
    }
    
     private void build() {
       try {
           this.bbal = new double[super.getLife()];
           //this.anndep = new double[super.getLife()];
           this.anndeprsl = new double[super.getLife()];
           this.ebal = new double[super.getLife()];
           
           
           //double DDLRate = (1.0/super.getLife()) * 2.0;
           double depSL = (super.getCost() - super.getSalvage()) / super.getLife();
           
           
           //double depWRK = 0;
           
           
           this.bbal[0] = super.getCost();
          // this.bbal[0][SL] = this.cost;
           for(int i=0; i < super.getLife(); i++) {
               if (i > 0) {
                   this.bbal[i] = this.ebal[i-1];
                   //this.bbal[i][SL] = this.ebal[i-1][SL];
               }
               
             // depWRK = this.bbal[i] * DDLRate;
               
               
              /* if (depWRK < depSL) {
                   depWRK = depSL; // crossover DDL to SL values
                   
               } 
               if ( (this.bbal[i] - depWRK) < super.getSalvage()) {
                   //to much depreciation would go below salvage
                   depWRK = this.bbal[i] - super.getSalvage();
               
           } */
               this.anndeprsl[i] = depSL;
               //this.anndep[i] = depWRK;
              // this.ebal[i] = this.bbal[i] - depWRK;
              this.ebal[i] = this.bbal[i]- depSL;
           }// end of for
           this.built = true; 
        
       } catch (Exception e) {
           super.SetErrorMsg("Build Error: " + e.getMessage());
           this.built = false;
           
           
       }
       
       
   }
    
    public double getAnnDepr(int yr) {
       if (!this.built) {
           if (isValid()) {
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > super.getLife()) {
           super.SetErrorMsg( " Request for year outside asset life");
           return -1;
       }
       
   return this.anndeprsl[yr-1]; 
   }

   
  /* public double getAnnDep(int yr) {
        if (!this.built) {
           if (isValid()) {
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > super.getLife()) {
           super.SetErrorMsg(" Request for year outside asset life");
           return -1;
       }
     
        
           return this.anndep[yr-1]; 
           
  } */
   
      public double getBegBal(int yr) {
       if (!this.built) {
           if (isValid()) {
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > super.getLife()) {
           super.SetErrorMsg(" Request for year outside asset life");
           return -1;
       }
       
           return this.bbal[yr-1];
      }
  
     
   public double getEndBal(int yr) {
      if (!this.built) {
           if (isValid()) { 
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > super.getLife()) {
           super.SetErrorMsg(" Request for year outside asset life");
           return -1;
       }
      
           return this.ebal[yr-1];
      
   }      
   
    
    
    
}
