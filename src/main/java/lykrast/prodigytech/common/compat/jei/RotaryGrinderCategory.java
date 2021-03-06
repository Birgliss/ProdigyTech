package lykrast.prodigytech.common.compat.jei;

import java.util.ArrayList;
import java.util.List;

import lykrast.prodigytech.common.recipe.RotaryGrinderManager;
import lykrast.prodigytech.common.recipe.SimpleRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;

public class RotaryGrinderCategory extends ProdigyCategory<SimpleRecipeWrapper> {
	public static final String UID = "ptgrinder";

	public RotaryGrinderCategory(IGuiHelper guiHelper) {
		super(guiHelper, guiHelper.createDrawable(ProdigyTechJEI.GUI, 0, 36, 82, 26), UID);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SimpleRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 4);
		guiItemStacks.init(1, false, 60, 4);

		guiItemStacks.set(ingredients);
	}

	public static void registerRecipes(IModRegistry registry)
	{
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		List<SimpleRecipeWrapper> list = new ArrayList<>();
		
		List<SimpleRecipe> recipes = RotaryGrinderManager.INSTANCE.getAllRecipes();
		recipes.stream().sorted().forEachOrdered(recipe -> list.add(new SimpleRecipeWrapper(recipe, guiHelper)));
		
		registry.addRecipes(list, UID);
	}

}
