package com.example.recipe_app.controller;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;
import com.example.recipe_app.logic.IngredientLogic;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IngredientController {

    /**
     * 材料の業務ロジック
     */
    @Autowired
    private IngredientLogic logic;

    /**
     * 【GET】一覧を検索します。
     *
     * @param ingredientCondDto 検索条件
     * @return 結果
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IngredientDto>> select(IngredientCondDto ingredientCondDto) {
        List<IngredientDto> list = logic.select(ingredientCondDto);
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    /**
     * 【GET】1件を検索します。
     *
     * @param id プライマリキー
     * @return 結果
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientDto> selectOne(@PathVariable("id") Long id) {
        IngredientDto ingredientDto = logic.selectOne(id);
        return null == ingredientDto ? ResponseEntity.noContent().build() : ResponseEntity.ok(ingredientDto);

    }

    /**
     * 【POST】1件を追加します。
     *
     * @param ingredientDto 追加条件
     * @return {@link Void}
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@RequestBody IngredientDto ingredientDto) throws NotFoundException {
        logic.insert(ingredientDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 【PUT】1件を更新します。
     *
     * @param id            プライマリキー
     * @param ingredientDto 更新条件
     * @return {@link Void}
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody IngredientDto ingredientDto) {
        logic.update(id, ingredientDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 【DELETE】1件を削除します。
     *
     * @param id プライマリキー
     * @return {@link Void}
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        logic.delete(id);
        return ResponseEntity.ok().build();
    }


}
