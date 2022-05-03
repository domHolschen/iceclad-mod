package IcecladMod.actions;

import IcecladMod.cardmodifiers.BriskCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;

public class GainBriskOnKillAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCard card = null;

    public GainBriskOnKillAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
    }
    
    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_MED && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            target.damage(info);
            if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Minion")) {
                ArrayList<AbstractCard> possibleCards = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (!CardModifierManager.hasModifier(c, new BriskCardModifier().identifier(c)))
                        possibleCards.add(c);
                }
                if (!possibleCards.isEmpty()) {
                    card = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size()-1));
                    CardModifierManager.addModifier(card, new BriskCardModifier());
                    AbstractDungeon.player.bottledCardUpgradeCheck(card);
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
        if (isDone && card != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy()));
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }
    }
}
