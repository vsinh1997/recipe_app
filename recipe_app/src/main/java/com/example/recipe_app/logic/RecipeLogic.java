package com.example.recipe_app.logic;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;
import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;
import com.example.recipe_app.mapper.IngredientMapper;
import com.example.recipe_app.mapper.RecipeMapper;
import org.springframework.transaction.annotation.Transactional;

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

        int recipeInsertCount = recipeMapper.insert(dto);
        insertOrUpdateIngredients(dto.getId(), dto.getIngredientList(), true);
        return recipeInsertCount;


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
        int updatedRecipeCount = recipeMapper.update(dto);
//        insertOrUpdateIngredients(dto.getId(), dto.getIngredientList(), false);
        return updatedRecipeCount;
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

        deleteIngredientsByRecipeId(id);
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(id);
        return recipeMapper.delete(recipeDto);
    }

    /**
     * 条件を指定し材料情報を削除します。
     *
     * @param recipeId レシピ番号
     */
    private void deleteIngredientsByRecipeId(Long recipeId) {
        IngredientCondDto ingredientCondDto = new IngredientCondDto();
        ingredientCondDto.setRecipeId(recipeId);
        List<IngredientDto> existsIngredientDtoList = ingredientMapper.select(ingredientCondDto);
        if (null == existsIngredientDtoList || existsIngredientDtoList.isEmpty()) {
            return;
        }

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(existsIngredientDtoList.get(0).getId());
        ingredientMapper.delete(ingredientDto);
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

    /**
     * 材料情報を登録・更新します。
     *
     * @param recipeId       レシピ番号
     * @param ingredientList 材料リスト
     * @param insertFlg      登録フラグ
     */
    private void insertOrUpdateIngredients(Long recipeId, List<IngredientDto> ingredientList, boolean insertFlg) {

        if (null == recipeId || null == ingredientList || ingredientList.isEmpty()) {
            return;
        }

        for (IngredientDto ingredientDto : ingredientList) {
            ingredientDto.setRecipeId(recipeId);
            if (insertFlg) {
                ingredientMapper.insert(ingredientDto);
            } else {
                ingredientMapper.update(ingredientDto);
            }
        }

    }


}
