package IcecladMod.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BriskCardModifier extends AbstractCardModifier {
    public BriskCardModifier makeCopy() {
        return new BriskCardModifier();
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "icecladmod:Brisk. NL "+ rawDescription;
    }

}
