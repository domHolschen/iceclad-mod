package IcecladMod.actions;

import IcecladMod.powers.CommonPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class AbsorbAction extends AbstractGameAction {
    private DamageInfo info;
    private int healAmount;

    public AbsorbAction(AbstractCreature target, DamageInfo info, int healAmount) {
        this.info = info;
        setValues(target, info);
        this.healAmount = healAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
    }
    
    @Override
    public void update() {
        if (this.target != null) {
            this.target.damage(this.info);
            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AbstractDungeon.player.heal(healAmount, true);
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        this.isDone = true;
    }
}
