package com.example.recipe_app.controller;

import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;
import com.example.recipe_app.logic.RecipeLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecipeController {

    /**
     * レシピロジック
     */
    private final RecipeLogic logic;

    /**
     * 【GET】一覧を取得します。
     *
     * @param cond 検索条件
     * @return 結果
     */
    @GetMapping("")
    public ResponseEntity<List<RecipeDto>> select(RecipeCondDto cond) {
        return ResponseEntity.ok(logic.select(cond));
    }

    /**
     * 【GET】1件を取得します。
     *
     * @param id プライマリキー
     * @return 結果
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> selectOne(@PathVariable("id") Long id) {
        RecipeDto recipeDto = logic.selectOne(id);
        return (null == recipeDto) ? ResponseEntity.notFound().build() : ResponseEntity.ok(recipeDto);
    }

    /**
     * 【POST】1件を追加します。
     *
     * @param dto 追加条件
     * @return 追加件数
     */
    @PostMapping("")
    public ResponseEntity<Void> insert(@RequestBody RecipeDto dto) {
        int createdCount = logic.insert(dto);
        return (createdCount > 0) ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    /**
     * 【PUT】1件を更新します。
     *
     * @param id  プライマリキー
     * @param dto 更新条件
     * @return 更新件数
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody RecipeDto dto) {
        int updateCount = logic.update(id, dto);
        return (updateCount > 0) ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }

    /**
     * 【DELETE】1件を削除します。
     *
     * @param id プライマリキー
     * @return 削除件数
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        int deletedCount = logic.delete(id);
        return (deletedCount > 0) ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();

    }

}
