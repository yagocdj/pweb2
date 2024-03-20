package br.edu.ifpb.pweb2.bitbank.service;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import org.springframework.stereotype.Service;

@Service
public class CorrentistaService {

    public boolean isCorrentistaDataValid(Correntista correntista) {
        return isNameValid(correntista.getNome()) &&
                isFieldNotBlank(correntista.getEmail()) &&
                isFieldNotBlank(correntista.getSenha());
    }

    /**
     * Checks if the name is not blank (white space, tab or empty) and if the
     * name's length is within the 50-character limit.
     * @param name a string that corresponds to the correntista's name.
     * @return `true` if the name obeys to the rules cited above.
     * Otherwise, it returns `false`.
     */
    public boolean isNameValid(String name) {
        return isFieldNotBlank(name) && isNameSmallerThan50Chars(name);
    }

    /**
     * Checks if a field (String) is not a blank value (white space, tab, or empty)
     * @param value a string to be checked
     * @return `true` if the value is not blank.
     * Otherwise, the return is `false`.
     */
    public boolean isFieldNotBlank(String value) {
        return !value.isBlank();
    }

    /**
     * Checks if the provided name has at most 50 characters.
     *
     * @param name The name to be checked.
     * @return `true` if the name has at most 50 characters, `false` otherwise.
     */
    public boolean isNameSmallerThan50Chars(String name) {
        return name.length() <= 50;
    }
}
