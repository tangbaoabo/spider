<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 30px">

    <div class="row clearfix" style="padding-bottom: 20px">
        <div class="col-md-12 column">
            <h3 class="text-center">
                真爱网全局信息检索系统
            </h3>
        </div>
    </div>
    <div class="row clearfix" style="padding-bottom: 10px">
        <!--主要的区域-->
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-3 column">
                </div>
                <div class="col-md-6 column">
                    <form action="/search" method="POST">
                        <div class="input-group">
                            <input type="text" class="form-control " placeholder="请输入es查询条件" value="${itemResult.query}"
                                   name="question"/>
                            <span class="input-group-btn">
                            <button type="submit" class="btn btn-info btn-search glyphicon glyphicon-search"
                                    style="margin-top: -1px;"></button>
                   </span>
                        </div>
                    </form>
                </div>
                <div class="col-md-3 column">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row clearfix" style="padding-bottom: 10px">
    <div class="col-md-12 column">
        <p class="text-center text-success">
            为你找到相关结果 <strong>${itemResult.count}</strong>
            个，显示从第<strong>${itemResult.start}</strong>到<strong>${itemResult.itemList?size}</strong>个
        </p>
    </div>
</div>
<div class="row clearfix">
    <div class="col-md-12 column">
        <div class="row clearfix">
            <div class="col-md-1 column">
            </div>
            <div class="col-md-10 column">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>昵称</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>身高</th>
                        <th>体重</th>
                        <th>工作地</th>
                        <th>收入</th>
                        <th>婚姻</th>
                        <th>户口</th>
                        <th>星座</th>
                        <th>房子</th>
                        <th>车子</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if itemResult.itemList??>
                        <#list itemResult.itemList as itemList>
                            <tr>
                                <td><a href="${itemList.url!""}" target="_blank">${itemList.payLoad.name!""}</a></td>
                                <td>${itemList.payLoad.gender!""}</td>
                                <td>${itemList.payLoad.age!""}</td>
                                <td>${itemList.payLoad.height!""}</td>
                                <td>${itemList.payLoad.weight!""}</td>
                                <td>${itemList.payLoad.workplace!""}</td>
                                <td>${itemList.payLoad.income!""}</td>
                                <td>${itemList.payLoad.marriage!""}</td>
                                <td>${itemList.payLoad.hokou!""}</td>
                                <td>${itemList.payLoad.xinzuo!""}</td>
                                <td>${itemList.payLoad.car!""}</td>
                                <td>${itemList.payLoad.house!""}</td>
                            </tr>
                        </#list>
                    <#else>
                        <div class="row clearfix" style="padding-bottom: 10px">
                            <div class="col-md-12 column">
                                <p class="text-center text-danger">
                                    <strong>对不起，没有找到相关用户</strong>
                                </p>
                            </div>
                        </div>
                    </#if>
                    </tbody>
                </table>
            </div>
            <div class="col-md-1 column">
            </div>
        </div>
        <nav style="text-align: center">
            <ul class="pagination">
                <#if itemResult.pre gte 0>
                <li><a id = "preNum" href="search?question=${itemResult.query}&from=${itemResult.pre}">上一页</a></li>
                </#if>
                <li><a id = "nextNum" href="search?question=${itemResult.query}&from=${itemResult.next}">下一页</a></li>
            </ul>
        </nav>
    </div>
</div>
</div>
</body>
</html>