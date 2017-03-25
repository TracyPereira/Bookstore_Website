<%-- 
    Document   : home
    Created on : Sep 24, 2016, 6:52:25 PM
    Author     : MY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Foundation | Welcome</title>
    <link rel="stylesheet" href="foundation.css">
     <script src="js/jquery.js"></script>
    <script src="css/foundation.js"></script>
  </head>
  <body>
    
    <!-- Start Top Bar -->
    <div class="top-bar">
      <div class="top-bar-left">
        <ul class="menu">
          <li class="menu-text">Blog</li>
          <li><a href="#">One</a></li>
          <li><a href="#">Two</a></li>
          <li><a href="#">Three</a></li>
        </ul>
      </div>
    </div>
    <!-- End Top Bar -->

    <div class="callout large primary">
      <div class="row column text-center">
          <h1>Hello, ${sessionScope.currentSessionUser.username}</h1>
        <h2 class="subheader">Such a Simple Blog Layout</h2>
      </div>
    </div>
    
    <!-- We can now combine rows and columns when there's only one column in that row -->
    <div class="row medium-8 large-7 columns">
      <div class="blog-post">
        <h3>Awesome blog post title <small>3/6/2015</small></h3>
        <img class="thumbnail" src="http://placehold.it/850x350">
        <p>Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus vestibulum. Nulla at nulla justo, eget luctus.</p>
        <div class="callout">
          <ul class="menu simple">
            <li><a href="#">Author: Mike Mikers</a></li>
            <li><a href="#">Comments: 3</a></li>
          </ul>
        </div>
      </div>

      <div class="blog-post">
        <h3>Awesome blog post title <small>3/6/2015</small></h3>
        <img class="thumbnail" src="http://placehold.it/850x350">
        <p>Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus vestibulum. Nulla at nulla justo, eget luctus.</p>
        <div class="callout">
          <ul class="menu simple">
            <li><a href="#">Author: Mike Mikers</a></li>
            <li><a href="#">Comments: 3</a></li>
          </ul>
        </div>
      </div>

      <div class="blog-post">
        <h3>Awesome blog post title <small>3/6/2015</small></h3>
        <img class="thumbnail" src="http://placehold.it/850x350">
        <p>Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus vestibulum. Nulla at nulla justo, eget luctus.</p>
        <div class="callout">
          <ul class="menu simple">
            <li><a href="#">Author: Mike Mikers</a></li>
            <li><a href="#">Comments: 3</a></li>
          </ul>
        </div>
      </div>

      <div class="blog-post">
        <h3>Awesome blog post title <small>3/6/2015</small></h3>
        <img class="thumbnail" src="http://placehold.it/850x350">
        <p>Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus vestibulum. Nulla at nulla justo, eget luctus.</p>
        <div class="callout">
          <ul class="menu simple">
            <li><a href="#">Author: Mike Mikers</a></li>
            <li><a href="#">Comments: 3</a></li>
          </ul>
        </div>
      </div>
    </div>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>


    