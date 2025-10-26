package com.example.recipe_app.logic;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;
import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;
import com.example.recipe_app.mapper.IngredientMapper;
import com.example.recipe_app.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeLogic {

    /**
     * レシピマッパー
     */
    @Autowired
    private RecipeMapper recipeMapper;

    /**
     * 材料マッパー
     */
    @Autowired
    private IngredientMapper ingredientMapper;

    /**
     * 条件を指定し一覧を検索します。
     *
     * @param cond 条件
     * @return 結果
     */
    public List<RecipeDto> select(RecipeCondDto cond) {

        List<RecipeDto> recipeDtoList = recipeMapper.select(cond);

        if (null == recipeDtoList || recipeDtoList.isEmpty()) {
            return Collections.emptyList();
        }

        for (RecipeDto recipeDto : recipeDtoList) {
            recipeDto.setIngredientList(getIngredientList(recipeDto.getId()));
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

        if (null == id) {
            return null;
        }

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(id);
        RecipeDto result = recipeMapper.selectOne(recipeDto);

        if (null != result) {
            result.setIngredientList(getIngredientList(result.getId()));
        }
        return result;

    }

    /**
     * データを追加します。
     *
     * @param dto 追加情報
     * @return 件数
     */
    @Transactional
    public int insert(RecipeDto dto) {

        if (null == dto) {
            return 0;
        }

        return recipeMapper.insert(dto);

    }


    /**
     * プライマリキーを指定しデータを更新します。
     *
     * @param id  プライマリキー
     * @param dto 更新情報
     * @return 件数
     */
    @Transactional
    public int update(Long id, RecipeDto dto) {

        if (null == id || null == dto) {
            return 0;
        }

        if (!recipeExists(id)) {
            return 0;
        }

        dto.setId(id);

        return recipeMapper.update(dto);

    }

    /**
     * プライマリキーを指定しデータを削除します。
     *
     * @param id プライマリキー
     * @return 件数
     */
    @Transactional
    public int delete(Long id) {

        if (null == id) {
            return 0;
        }

        if (!recipeExists(id)) {
            return 0;
        }

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(id);
        return recipeMapper.delete(recipeDto);
    }


    /**
     * データが存在しないかどうか判定します。
     *
     * @param id プライマリキー
     * @return true：存在 false：不在
     */
    private boolean recipeExists(Long id) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(id);
        return null != recipeMapper.selectOne(recipeDto);
    }

    /**
     * 材料一覧を取得します。
     *
     * @param recipeId レシピ番号
     * @return 結果
     */
    private List<IngredientDto> getIngredientList(Long recipeId) {
        if (null == recipeId) {
            return Collections.emptyList();
        }
        IngredientCondDto ingredientCondDto = new IngredientCondDto();
        ingredientCondDto.setRecipeId(recipeId);
        return ingredientMapper.select(ingredientCondDto);

    }

}
