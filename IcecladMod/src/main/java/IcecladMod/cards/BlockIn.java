package IcecladMod.cards;

import IcecladMod.IcecladMod;
import IcecladMod.cardmodifiers.BriskCardModifier;
import IcecladMod.characters.IcecladCharacter;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static IcecladMod.IcecladMod.makeCardPath;

public class BlockIn extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = IcecladMod.makeID(BlockIn.class.getSimpleName());
    public static final String IMG = makeCardPath("BlockInPlaceholder.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = IcecladCharacter.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public BlockIn() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        CardModifierManager.addModifier(this, new BriskCardModifier());
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
