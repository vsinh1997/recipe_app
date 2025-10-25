package com.example.recipe_app.logic;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;
import com.example.recipe_app.mapper.IngredientMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientLogic {

    @Autowired
    private IngredientMapper ingredientMapper;

    /**
     * 材料一覧を検索します。
     *
     * @param ingredientCondDto 検索条件
     * @return 結果
     */
    public List<IngredientDto> select(IngredientCondDto ingredientCondDto) {
        return ingredientMapper.select(ingredientCondDto);
    }

    /**
     * 材料情報を検索します。
     *
     * @param id プライマリキー
     * @return 結果
     */
    public IngredientDto selectOne(Long id) {
        if (null == id) {
            return null;
        }
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(id);
        return ingredientMapper.selectOne(ingredientDto);
    }

    /**
     * 材料を１レコード追加します。
     *
     * @param ingredientDto 追加条件
     */
    public void insert(@NotNull IngredientDto ingredientDto) throws NotFoundException {
        if (!isValidInsertOrUpdate(ingredientDto.getId(), true)) {
            throw new NotFoundException("Ingredient not found");
        }
        ingredientMapper.insert(ingredientDto);
    }

    /**
     * 材料を１レコード更新します。
     *
     * @param id            プライマリキー
     * @param ingredientDto 追加条件
     */
    public void update(Long id, IngredientDto ingredientDto) {
        if (!isValidInsertOrUpdate(id, false)) {
            return;
        }
        ingredientDto.setId(id);
        ingredientMapper.update(ingredientDto);
    }

    /**
     * 材料を１レコード削除します。
     *
     * @param id プライマリキー
     */
    public void delete(Long id) {
        if (!isValidInsertOrUpdate(id, false)) {
            return;
        }
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(id);
        ingredientMapper.delete(ingredientDto);
    }

    /**
     * 属性チェックを行います。
     *
     * @param id       プライマリキー
     * @param isInsert 追加フラグ（true：追加、false：更新）
     * @return true：追加・更新可    false：追加・更新不可
     */
    private boolean isValidInsertOrUpdate(Long id, boolean isInsert) {
        if (isInsert) {
            return null == selectOne(id);
        } else {
            if (null == id) {
                return false;
            }
            return null != selectOne(id);
        }
    }

}
