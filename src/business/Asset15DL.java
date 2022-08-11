
package business;

/**
 *
 * @author amadou
 */
public class Asset15DL extends AssetDL {
    public static final double FACTOR = 1.5;
    
    public Asset15DL() {
        super();
    }
    public Asset15DL(String nm, double c, double s, int lf) {
        super(nm,c,s,lf,FACTOR);
        
    }
    
}
