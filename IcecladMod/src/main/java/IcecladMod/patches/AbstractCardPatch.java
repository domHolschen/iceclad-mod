package IcecladMod.patches;

import IcecladMod.cardmodifiers.BriskCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractCardPatch {
    //Makes brisk cards free if there are no other played cards this turn.
    @SpirePatch(
            clz = AbstractCard.class,
            method = "freeToPlay"
    )
    public static class BriskPatch {
        @SpirePrefixPatch(
        )
        public static SpireReturn<Boolean> freeToPlay (AbstractCard c) {
            if (CardModifierManager.hasModifier(c, new BriskCardModifier().identifier(c)) && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
                return SpireReturn.Return(AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT);
            }
            return SpireReturn.Continue();
        }
    }
}


