## コードの自動生成
`./mvnw clean compile` を実行することで、target配下にOpenAPI Specificationをもとに自動生成されるコードを吐き出せる
 
### delegateパターン
下記２つのオプションによって、Delegateパターンによるクラス生成が行われる。
```
<delegatePattern>true</delegatePattern>
<interfaceOnly>false</interfaceOnly>
```
controllerでいうと、これで生成されるのは

- controllerクラス
- controllerのインタフェース
- controllerのデリゲート

の３つである。
controllerクラスはtarget配下にクラスファイルだけ生成されるわけだが、このファイルをアレコレする必要性はまったくない。  
デリゲートがインジェクションされるようになっているので、controller内で行う実処理を完全に委譲することで、自動生成コードのメリットを活かせるようになっている。

### デメリット
controllerを自動生成に預けるのでcontroller内で書かないといけない例外処理のハンドラなんかは利用できなくなる。  
そのため、try-catchでがんばるか、グローバルなとこで@ControllerAdviceするしかないだろう。