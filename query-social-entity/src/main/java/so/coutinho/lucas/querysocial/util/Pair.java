package so.coutinho.lucas.querysocial.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Pair<X, Y> {

    private X x;
    private Y y;

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
