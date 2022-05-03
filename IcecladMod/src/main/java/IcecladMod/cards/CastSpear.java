package IcecladMod.cards;

import IcecladMod.IcecladMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import IcecladMod.characters.IcecladCharacter;

import static IcecladMod.IcecladMod.makeCardPath;

public class CastSpear extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * A Better Defend Gain 1 Plated Armor. Affected by Dexterity.
     */

    // TEXT DECLARATION 

    public static final String ID = IcecladMod.makeID(CastSpear.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = IcecladCharacter.Enums.COLOR_GRAY;

    private static final int COST = 1;

    // /STAT DECLARATION/


    public CastSpear() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new Spear();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, false, false, false));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            this.cardsToPreview.upgrade();
            initializeDescription();
        }
    }
}