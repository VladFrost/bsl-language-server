# Использование НайтиПоНаименованию и НайтиПоКоду

* **Применимость:** BSL (только для BSL)
* **Тип:** CODE_SMELL (дефект кода)
* **Серьезность:** MAJOR (важная)

Диагностика отлавливает использование методов "НайтиПоНаименованию" или "НайтиПоКоду", используюя "хардкод".

Например:
```bsl
Должность = Справочники.Должности.НайтиПоНаименованию("Ведущий бухгалтер");
```
или
```bsl
Должность = Справочники.Должности.НайтиПоКоду("00-0000001");
```

Допустимо использование:
```bsl
Справочники.Валюты.НайтиПоКоду(ТекущиеДанные.КодВалютыЦифровой);
```
```bsl
Справочники.КлассификаторБанков.НайтиПоКоду(СведенияОБанке.БИК);
```