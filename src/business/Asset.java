
package business;

/**
 *
 * @author amadou
 */
public class Asset {
    private String AssetNm, Errmsg;
    private double cost, salvage;
    private int life;
    /*private double[][] bbal, anndep, ebal, anndeprsl;
    private boolean built;
    private static final int SL = 0; // column for SL values;
    private static final int DDL = 1; */
    
    public Asset() {
     this.AssetNm = "";
     this.Errmsg = "";
     this.cost = 0;
     this.salvage = 0;
     this.life = 0;
    //this.built = false;
        
    }
    
    public Asset(String AssetNm, double cost, double salvage, int lf) {
        this.AssetNm = AssetNm;
        this.cost = cost;
        this.salvage = salvage;
        this.life = lf;
        this.Errmsg = "";
       // this.built = false;
       /* if(isValid()) {
            build();
        } */
    }
    
    protected boolean isValid() {
        this.Errmsg = "";
        if (this.AssetNm.isEmpty()) {
            this.Errmsg += "Missing Asset Name";
        }
        if (this.cost <= 0) {
            this.Errmsg += "illegal cost value. ";
        }
        if (this.salvage < 0) {
            this.Errmsg += "illegal salvage value";
        }
        if (this.life <= 0) {
            this.Errmsg += "illegal life value. ";
        }
        if (this.salvage >= this.cost) {
            this.Errmsg += "illegal Salvage: must be less than cost.";
        }
//        if (this.Errmsg.isEmpty()) {
//            return true;
//        }
//        return false;
    return this.Errmsg.isEmpty();
    
}
  
    /*public double getBegBal(int yr, String dtype) {
       if (!this.built) {
           if (isValid()) {
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > this.life) {
           this.Errmsg = " Request for year outside asset life";
           return -1;
       }
       if (dtype.equalsIgnoreCase("S")) {
           return this.bbal[yr-1][SL];
      
       } else if (dtype.equalsIgnoreCase("D")) {
           return this.bbal[yr-1][DDL];
      
       } else {
           this.Errmsg = "Unknown Depreciation type requested. ";
           return -1;
       }
     }
    */
     
   public double getCost() {
       return this.cost;
   }

   
   public double getSalvage() {
       return this.salvage;
   }
   
   public int getLife() {
       return this.life;
   }
   
     
     
   public String getErrorMsg() {
       return this.Errmsg;
   }
   protected void SetErrorMsg(String msg) {
       this.Errmsg = msg;
   }
   
 /*  public double getAnnDepr(int yr) {
       if (!this.built) {
           if (isValid()) {
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > this.life) {
           this.Errmsg = " Request for year outside asset life";
           return -1;
       }
       
   return this.anndeprsl[yr-1][SL]; 
   }

   
   public double getAnnDep(int yr) {
        if (!this.built) {
           if (isValid()) {
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > this.life) {
           this.Errmsg = " Request for year outside asset life";
           return -1;
       }
     
        
           return this.anndep[yr-1][DDL]; 
   }
     
   public double getEndBal(int yr, String dtype) {
      if (!this.built) {
           if (isValid()) { 
               build();
           }
           if (!this.built) {
               return -1;
           }
         
       }
       if (yr < 1 || yr > this.life) {
           this.Errmsg = " Request for year outside asset life";
           return -1;
       }
       if (dtype.equalsIgnoreCase("S")) {
           return this.ebal[yr-1][SL];
      
       } else if (dtype.equalsIgnoreCase("D")) {
           return this.ebal[yr-1][DDL];
      
       } else {
           this.Errmsg = "Unknown Depreciation type requested. ";
           return -1;
       }
     } */
   
    
   
}
