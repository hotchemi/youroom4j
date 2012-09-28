[![Build Status](https://secure.travis-ci.org/rochefort/gem-search.png)](http://travis-ci.org/rochefort/gem-search)
youRoom4J
=========
youRoom4Jは<a target="blank" href="https://www.youroom.in">youRoom</a>が提供している<a target="blank" href="http://apidoc.youroom.in">API</a>のJavaラッパーです。<br/>
youRoom4Jは非公式のライブラリです｡

Twitter4J は JSON レスポンスの解析のため JSON.org のソフトウェアを含んでいます。JSON.org のソフトウェアのライセンスについてはThe JSON Licenseをご覧ください。

※Androidには現在対応していません｡<br/>
※Enterprise methodは近日対応予定です｡

##使い方
### インスタンス生成
```java
Youroom youYoom = YouRoomFactory.getInstance();
youroom.setOAuthConsumer("consumerKey", "consumerSecret");
youroom.setOAuthAccessToken("accessToken", "accessTokenSecret");
```
### タイムライン(ホーム)の取得
ホームタイムラインを返します｡
```java
List<Entry> list = youRoom.getHomeTimeline(new Paging());
```
### タイムライン(ルーム)の取得
指定したルームのタイムラインを返します｡
```java
List<Entry> list = youRoom.getRoomTimeline(new Paging());
```
### エントリの取得
指定したエントリの情報を返します｡
```java
Entry entry = youRoom.showEntry(int id, int groupParam);
```
### エントリの作成
YouRoom#createEntry()メソッドで指定したルームにエントリを投稿することができます。
```java
Entry entry = youRoom.createEntry(String content, int parentId, int groupParam);
```
### エントリの更新
YouRoom#updateEntry()メソッドで指定したエントリを更新することができます。
```java
Entry entry = youRoom.createEntry(int id, String content, int groupParam);
```
### エントリの削除
YouRoom#deleteEntry()メソッドで指定したエントリを削除することができます。
```java
Entry entry = youRoom.destroyEntry(int id, int groupParam);
```
### アタッチメントの取得
YouRoom#showAttachment()メソッドで指定したエントリを削除することができます。
```java
byte[] attachment = youRoom.showAttachment(int id, int groupParam);
```
### 参加しているグループの取得 ###
```java
List<MyGroup> groups = youRoom.getMyGroups();
```
### User/verify_credentialsの取得 ###
```java
List<User> groups = youRoom.verifyCredentials();
```    
### User/verify_credentialsの取得 ###
```java
byte[] picture = youRoom.showPicture(int groupParam, int participationId);
```













パラメータの解説
----------------
リストの間に空行を挟むと、それぞれのリストに `<p>` タグが挿入され、行間が
広くなります。
 
    def MyFunction(param1, param2, ...)
 
+   `param1` :
    _パラメータ1_ の説明
 
+   `param2` :
    _パラメータ2_ の説明
 
関連情報
--------
### リンク、ネストしたリスト
1. [リンク1](<a href="http://example.com/" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">http://example.com/</a> "リンクのタイトル")
    * ![画像1](<a href="http://github.com/unicorn.png" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">http://github.com/unicorn.png</a> "画像のタイトル")
2. [リンク2][link]
    - [![画像2][image]](<a href="https://github.com/" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">https://github.com/</a>)
 
  [link]: <a href="http://example.com/" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">http://example.com/</a> "インデックス型のリンク"
  [image]: <a href="http://github.com/github.png" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">http://github.com/github.png</a> "インデックス型の画像"
 
### 引用、ネストした引用
> これは引用です。
>
> > スペースを挟んで `>` を重ねると、引用の中で引用ができますが、
> > GitHubの場合、1行前に空の引用が無いと、正しくマークアップされません。
 
ライセンス
----------
Copyright &copy; 2011 xxxxxx
Licensed under the [Apache License, Version 2.0][Apache]
Distributed under the [MIT License][mit].
Dual licensed under the [MIT license][MIT] and [GPL license][GPL].
 
[Apache]: <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">http://www.apache.org/licenses/LICENSE-2.0</a>
[MIT]: <a href="http://www.opensource.org/licenses/mit-license.php" target="_blank" rel="noreferrer" style="cursor:help;display:inline !important;">http://www.opensource.org/licenses/mit-license.php</a>
[GPL]: <a href="http://www.gnu.org/licenses/gpl.html" target="_blank