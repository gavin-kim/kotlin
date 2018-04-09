import java.util.Arrays;
import java.util.List;

public class Variance {
    public static void main(String[] args) {
        List<? extends Type> covarianceList = Arrays.asList(new Type());   // Producer: extends

        Type type = covarianceList.get(0);
        SuperType superType = covarianceList.get(0);
        Object object = covarianceList.get(0);

        List<? super Type> contravarianceList = Arrays.asList(new Type()); // Consumer: super

        contravarianceList.add(new Type());
        contravarianceList.add(new SubType());
    }

    public static void Invariance(List<Type> types) {
        /** Accept only own type **/
        Object object = types.get(0);
        Type type = types.get(0);
    }

    public static void Covariance(List<? extends Type> types) {
        /**
         * Accept subtypes
         * Not Accept supertypes
         */

        /** input */
        // Can not add anything because we don't know what subtype (ex. List<GrandSubType>)

        /** output */
        Type type = types.get(0);
        SuperType superType = types.get(0);
        Object object = types.get(0);
    }

    public static void Contravariance(List<? super Type> types) {
        /**
         * Not Accept subtypes
         * Accept supertypes
         */

        /** input **/
        Object object = types.get(0);

        /** output **/
        types.add(new Type()); // we know list has type or some supertype, so add any subclass of type
        types.add(new SubType());
    }

    public static void Bivariance(List<?> types) {
        /** Accept both supertypes and subtypes **/

        // we know only the root class (Object)
        Object object = types.get(0);
    }

    /** PECS - Producer-Extends, Consumer-Super */
    public static void producerExtendsConsumerSuper(List<? extends Type> listToGet, // covariance
                                                    List<? super Type> listToSet    // contravariance
    ) {
        listToGet.forEach(covariantType -> listToSet.add(covariantType));
    }
}

class SuperType {
    SuperType() {
    }
}

class Type extends SuperType {
    Type() {
    }
}

class SubType extends Type {
    SubType() {
    }
}