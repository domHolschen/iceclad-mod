package IcecladMod.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class RetreatCardModifier extends AbstractCardModifier {
    public RetreatCardModifier makeCopy() {
        return new RetreatCardModifier();
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!card.exhaust) {
            return rawDescription + " NL icecladmod:Retreat.";
        }
        return rawDescription;
    }

}
