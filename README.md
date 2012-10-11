youRoom4J [![Build Status](https://buildhive.cloudbees.com/job/hotchemi/job/youroom4j/badge/icon)](https://buildhive.cloudbees.com/job/hotchemi/job/youroom4j/)
=========
youRoom4Jは<a target="blank" href="https://www.youroom.in">youRoom</a>が提供している<a target="blank" href="http://apidoc.youroom.in">API</a>のJavaラッパーです。<br/>
youRoom4Jを使うとyouRoomのAPIを活用したアプリケーションを容易に開発することが出来ます｡<br/>
youRoom4Jは非公式のライブラリです｡

* OAuthに対応｡
* XAuthは対応予定｡
* Enterprise methodsは現在対応中｡
* Android､GAEは対応予定｡

##Source Code
プロジェクトのリポジトリには以下のURLからアクセスできます｡
```ruby
github: https://github.com/hotchemi/youroom4j
```
また､以下のようにgitを使って最新のソースコードをチェックアウトすることもできます。
```ruby
git clone git@github.com:hotchemi/youroom4j.git
```
##Usage
<a target="blank" href="https://github.com/hotchemi/youroom4j/downloads">Downloads</a>より最新版のjarを取得し､クラスパスを通して下さい｡<br/>
Javaが分かっていれば<a target="blank" href="http://youroom4j.herokuapp.com">JavaDoc</a>を見る事をお薦めします｡
##Sample Code
### 1. OAuth
OAuth認証を利用するとユーザーにメールアドレスとパスワードを提供してもらうことなくユーザのアカウントにアクセスできます｡<br/>
OAuthを利用するにはhttp://apidoc.youroom.in/authentication で事前に申請を行いconsumer key/secretを取得しておく必要があります｡<br/>
取得したconsumer key/secretはOAuthAuthorization#setOAuthConsumerクラスに設定します｡<br/>
この際､認証後にコールバックされるURLも同時に指定する必要があります｡
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

### 3. Room Timeline
YouRoom#getRoomTimeline()メソッドは指定したルームのタイムラインを返します｡<br/>
引数にPagingオブジェクトを指定します｡
```java
Paging paging = new Paging(int groupParam, String since, String searchQuery, boolean flat, int page, String readState);
List<Entry> list = youRoom.getRoomTimeline(paging);
```
+ `groupParam` :
  __Required.__ The subdomain of the room include entry to destroy.
+ `since` :
  Bigining time of fetch entries. Use the RFC 3339 timestamp format. _For example: 2005-08-09T10:57:00-08:00._
+ `searchQuery` :
  Keyword for search entries.
+ `flat` :
  If given _"true"_, response is include topics and comments and sorted by created_at.
+ `page` :
  Pagination entries.
+ `readState` :
  If given _"unread"_, response is include only unread topics.

### 4. Entry Show
YouRoom#showEntry()メソッドは指定したエントリの情報を返します｡
```java
Entry entry = youRoom.showEntry(int id, int groupParam);
```
+ `id` :
  __Required.__ The ID of the entry.
+ `groupParam` :
  __Required.__ The subdomain of the room include entry.

### 5. Create Entry
YouRoom#createEntry()メソッドで指定したルームにエントリを投稿することができます。
```java
Entry entry = youRoom.createEntry(String content, int parentId, int groupParam);
```
+ `content` :
  __Required.__ The text of Entry's content. Text over 140 characters will cause a 422 error to be returned from the API.
+ `parentId` :
  __Optional.__ The id of parent entry.
+ `groupParam` :
  __Required.__ The subdomain of the room include entry to create.

### 6. Update Entry
YouRoom#updateEntry()メソッドで指定したエントリを更新することができます。
```java
Entry entry = youRoom.createEntry(int id, String content, int groupParam);
```
+ `id` :
  __Required.__ The ID of the entry to update.
+ `content` :
  __Required.__ The text of Entry's content. Text over 140 characters will cause a 422 error to be returned from the API.
+ `groupParam` :
  __Required.__ The subdomain of the room include entry to update.

### 7. Destroy Entry
YouRoom#destroyEntry()メソッドで指定したエントリを削除することができます。
```java
Entry entry = youRoom.destroyEntry(int id, int groupParam);
```
+ `id` :
  __Required.__ The ID of the entry to destroy.
+ `groupParam` :
  __Required.__ The subdomain of the room include entry to destroy.

### 8. show Attachment
YouRoom#showAttachment()メソッドで指定したエントリの添付ファイルを取得します｡
```java
byte[] attachment = youRoom.showAttachment(int id, int groupParam);
```
+ `id` :
  __Required.__ The ID of the entry.
+ `groupParam` :
  __Required.__ The subdomain of the room include entry.

### 9. My Groups
YouRoom#getMyGroups()メソッドで所属しているルーム一覧を取得できます｡
```java
List<MyGroup> groups = youRoom.getMyGroups();
```
### 10. User/verify_credentials
YouRoom#verifyCredentials()メソッドでユーザアカウント情報を取得できます｡
```java
User user = youRoom.verifyCredentials();
```    
### 11. Show Picture
YouRoom#showPicture()メソッドでユーザのプロフィール画像を取得できます｡
```java
byte[] picture = youRoom.showPicture(int groupParam, int participationId);
```
+ `groupParam` :
  __Required.__ The subdomain of the room include entry.
+ `participationId` :
  __Required.__ The ID of the entry.

##ライセンス
youRoom4JはMIT Licenseに基づきリリースされています｡
```
The MIT License

Copyright (c) 2012 Shintaro Katafuchi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
* youRoom4JはXMLレスポンスの解析のため､<a target="blank" href="http://code.google.com/p/joox/">jOOX</a>のソフトウェアを含んでいます｡<br/>
jOOXのソフトウェアのライセンスについては<a target="blank" href="http://www.apache.org/licenses/">こちら</a>をご覧ください｡<br/>

* youRoom4JはOAuth認証のため､<a target="blank" href="https://github.com/fernandezpablo85/scribe-java">scribe-java</a>のソフトウェアを含んでいます｡<br/>
scribe-javaのソフトウェアのライセンスについては<a target="blank" href="https://github.com/fernandezpablo85/scribe-java/blob/master/LICENSE.txt">こちら</a>をご覧ください。