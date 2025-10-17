package com.example.recipe_app.logic;

import java.util.List;

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
	public RecipeDto selectOne(Long id) {
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(id);
		return mapper.selectOne(recipeDto);

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
	 * @param dto 更新情報
	 * @return 件数
	 */
	public int update(RecipeDto dto) {

		if (isExistRecipe(dto)) {
			return 0;
		}

		return mapper.update(dto);

	}

	/**
	 * プライマリキーを指定しデータを削除します。
	 *
	 * @param dto 削除条件
	 * @return 件数
	 */
	public int delete(RecipeDto dto) {

		if (isExistRecipe(dto)) {
			return 0;
		}

		return mapper.delete(dto);

	}

	/**
	 * データが存在しないかどうか判定します。
	 *
	 * @param dto 条件
	 * @return true：存在 false：不在
	 */
	private boolean isExistRecipe(RecipeDto dto) {

		if (null == mapper.selectOne(dto)) {

			return false;

		}

		return true;

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
