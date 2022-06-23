package IcecladMod.patches;

import IcecladMod.cardmodifiers.BriskCardModifier;
import IcecladMod.cardmodifiers.RetreatCardModifier;
import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public class UseCardActionPatch {
    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class UseCardActionInsertPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(UseCardAction __instance) {
            AbstractCard targetCard = ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");

            if (CardModifierManager.hasModifier(targetCard, new RetreatCardModifier().identifier(targetCard)) && !targetCard.exhaust) {
                targetCard.exhaustOnUseOnce = false;
                targetCard.dontTriggerOnUseCard = false;
                AbstractDungeon.player.hand.moveToDeck(targetCard, false);
                AbstractDungeon.actionManager.addToBottom(new HandCheckAction());
                __instance.isDone = true;

                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(UseCardAction.class, "reboundCard");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
