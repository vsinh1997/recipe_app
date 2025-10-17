package com.example.recipe_app.logic;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;
import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;
import com.example.recipe_app.mapper.IngredientMapper;
import com.example.recipe_app.mapper.RecipeMapper;

@Service
public class RecipeLogic {

	/**
	 * レシピマッパー
	 */
	@Autowired
	private RecipeMapper mapper;

	/**
	 * 材料マッパー
	 */
	@Autowired
	private IngredientMapper ingredientMapper;

	/**
	 * 条件を指定し一覧を検索します。
	 *
	 * @param dto 条件
	 * @return 結果
	 */
	public List<RecipeDto> select(RecipeCondDto dto) {

		List<RecipeDto> recipeDtoList = mapper.select(dto);

		if (null != recipeDtoList && !recipeDtoList.isEmpty()) {

			for (RecipeDto recipeDto : recipeDtoList) {

				recipeDto.setIngredientList(getIngredientList(recipeDto.getId()));

			}

		}
		return recipeDtoList;

	}

	/**
	 * プライマリキーを指定しデータを検索します。
	 *
	 * @param id プライマリキー
	 * @return 結果
	 */
	public RecipeDto selectOne(Long id)  {

		if (null != id) {
			return null;
		}

		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(id);

		RecipeDto result = mapper.selectOne(recipeDto);
		result.setIngredientList(getIngredientList(result.getId()));
        return result;

	}

	/**
	 * データを追加します。
	 *
	 * @param dto 追加情報
	 * @return 件数
	 */
	public int insert(RecipeDto dto) {

		return mapper.insert(dto);

	}

	/**
	 * プライマリキーを指定しデータを更新します。
	 * 
	 * @param id プライマリキー
	 * @param dto 更新情報
	 * @return 件数
	 */
	public int update(Long id, RecipeDto dto) {

		RecipeDto cond = new RecipeDto();
		cond.setId(id);

		if (!hasRecipe(cond)) {
			return 0;
		}

		cond.setName(dto.getName());
		cond.setDescription(dto.getDescription());
		cond.setDifficulty(dto.getDifficulty());
		cond.setCookingTime(dto.getCookingTime());
		cond.setCategoryId(dto.getCategoryId());
		return mapper.update(cond);

	}

	/**
	 * プライマリキーを指定しデータを削除します。
	 *
	 * @param id プライマリキー
	 * @return 件数
	 */
	public int delete(Long id) {

		RecipeDto cond = new RecipeDto();
		cond.setId(id);

		if (!hasRecipe(cond)) {
			return 0;
		}

		return mapper.delete(cond);

	}

	/**
	 * データが存在しないかどうか判定します。
	 *
	 * @param dto 条件
	 * @return true：存在 false：不在
	 */
	private boolean hasRecipe(RecipeDto dto) {
        return null != mapper.selectOne(dto);
    }

	/**
	 * 材料一覧を取得します。
	 *
	 * @param recipeId レシピ番号
	 * @return 結果
	 */
	private List<IngredientDto> getIngredientList(Long recipeId) {
		
		IngredientCondDto cond = new IngredientCondDto();
		cond.setRecipeId(recipeId);
		return ingredientMapper.select(cond);
		
	}

}
