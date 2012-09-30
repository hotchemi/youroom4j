youRoom4J [![Build Status](https://secure.travis-ci.org/rochefort/gem-search.png)](http://travis-ci.org/rochefort/gem-search)
=========
youRoom4Jは<a target="blank" href="https://www.youroom.in">youRoom</a>が提供している<a target="blank" href="http://apidoc.youroom.in">API</a>のJavaラッパーです。<br/>
youRoom4Jを使うとyouRoomのAPIを活用したアプリケーションを容易に開発することが出来ます｡<br/>

* OAuthに対応｡
* XAuthは今後対応予定｡
* Enterprise methodsは現在対応中｡
* Android､GAEは今後対応予定｡
* youRoom4Jは非公式のライブラリです｡

##Installation
Downloadsより最新版を取得し､youroom4j.jarにクラスパスを通して好きなメソッドを呼び出して下さい｡
##Code Sample
### 1. OAuth
OAuth認証を利用するとユーザーにメールアドレスとパスワードを提供してもらうことなくユーザのアカウントにアクセスできます｡<br/>
OAuthを利用するにはhttp://apidoc.youroom.in/authentication で事前に申請を行いconsumer key､consumer secretを取得しておく必要があります｡<br/>
取得したconsumer keyとconsumer secretはOAuthAuthorization#setOAuthConsumerクラスにセットします｡<br/>
この際､認証後にコールバックされてくるURLも同時に指定する必要があります｡
```java
OAuthAuthorization authorization= new OAuthAuthorization();
authorization.setOAuthConsumer("consumerKey", "consumerSecret", "callbackUrl");
```
以下のようにauthorization URLにユーザを誘導し､AccessTokenを取得する必要があります。
```java
public static void main(String[] args) throws Exception {
  OAuthAuthorization authorization= new OAuthAuthorization();
  authorization.setOAuthConsumer("consumerKey", "consumerSecret", "callbackUrl");
  
  System.out.println(authorization.getAuthorizationUrl());
  System.out.print("enter oauth_verifier:");
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String oauthVerifier = "";
  
  try {
    // コールバックURLについてくるoauth_verifier=以下を入力
    oauthVerifier = br.readLine();
  } catch (IOException e) {
    e.printStackTrace();
  }

  // accessToken.getAceessToken()とaccessToken.getAceessTokenSecret()を永続化
  AccessToken accessToken = authorization.getAccessToken(oauthVerifier);
  System.out.println("accessToken=" + accessToken.getAceessToken());
  System.out.println("accessTokenSecret" + accessToken.getAceessTokenSecret());
}
```
次回からはconsumer key/secretとaccess token/secretのみでユーザアカウントにアクセスできます｡
取得したconsumer key/secretとaccess token/secretを設定します｡
```java
Youroom youRoom = YouRoomFactory.getInstance();
youroom.setOAuthConsumer("consumerKey", "consumerSecret");
youroom.setOAuthAccessToken("accessToken", "accessTokenSecret");
```
### 2. Home Timeline
YouRoom#getHomeTimeline()メソッドはホームタイムラインを返します｡<br/>
引数にPagingオブジェクトを指定します｡
```java
Paging paging = new Paging(String since, boolean flat, int page, String readState);
List<Entry> list = youRoom.getHomeTimeline(paging);
```
+ `since` :
  Bigining time of fetch entries. Use the RFC 3339 timestamp format. _For example: 2005-08-09T10:57:00-08:00._
+ `flat` :
  If given _"true"_, response is include topics and comments and sorted by created_at.
+ `page` :
  Pagination entries.
+ `readState` :
  If given _"unread"_, response is include only unread topics.

### 3. Home Timeline
YouRoom#getRoomTimeline()メソッドは指定したルームのタイムラインを返します｡<br/>
引数にPagingオブジェクトを指定します｡
```java
Paging paging = new Paging(int groupParam, String since, String searchQuery, boolean flat, int page, String readState);
List<Entry> list = youRoom.getRoomTimeline(paging);
```
+ `since` :
  Bigining time of fetch entries. Use the RFC 3339 timestamp format. _For example: 2005-08-09T10:57:00-08:00._
+ `flat` :
  If given _"true"_, response is include topics and comments and sorted by created_at.
+ `page` :
  Pagination entries.
+ `readState` :
  If given _"unread"_, response is include only unread topics.

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

Twitter4J  JSON レスポンスの解析のため JSON.org のソフトウェアを含んでいます。JSON.org のソフトウェアのライセンスについてはThe JSON Licenseをご覧ください。













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