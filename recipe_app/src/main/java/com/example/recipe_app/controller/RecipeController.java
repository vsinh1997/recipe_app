package com.example.recipe_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;
import com.example.recipe_app.logic.RecipeLogic;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@CrossOrigin("*")
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
        return new ResponseEntity<List<RecipeDto>>(logic.select(cond), HttpStatus.OK);
    }

    /**
     * 【GET】1件を取得します。
     *
     * @param id プライマリキー
     * @return 結果
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> selectOne(@PathVariable("id") Long id) {

        RecipeDto result = logic.selectOne(id);

        if (null == result) {
            return new ResponseEntity<RecipeDto>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<RecipeDto>(result, HttpStatus.OK);
    }

    /**
     * 【POST】1件を追加します。
     *
     * @param dto 追加条件
     * @return 追加件数
     */
    @PostMapping("")
    public ResponseEntity<Integer> insert(@RequestBody RecipeDto dto) {
        return new ResponseEntity<Integer>(logic.insert(dto), HttpStatus.OK);
    }

    /**
     * 【PUT】1件を更新します。
     *
     * @param id  プライマリキー
     * @param dto 更新条件
     * @return 更新件数
     */
    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody RecipeDto dto) {
        return new ResponseEntity<Integer>(logic.update(id, dto), HttpStatus.OK);
    }

    /**
     * 【DELETE】1件を削除します。
     *
     * @param id プライマリキー
     * @return 削除件数
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<Integer>(logic.delete(id), HttpStatus.OK);
    }

}
