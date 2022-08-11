
package business;

/**
 *
 * @author AMADOU
 */
public class AssetDDL  extends AssetDL {
    public static final double FACTOR = 2.0;
    
    public AssetDDL() {
        super();
    }
    public AssetDDL(String nm, double c, double s, int lf) {
        super(nm,c,s,lf,FACTOR);
        
    }
    
}
