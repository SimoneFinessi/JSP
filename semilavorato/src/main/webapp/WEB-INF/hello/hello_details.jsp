<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">
            <title>HELLO PAGE!</title>
            <link href="/static/css/bootstrap.min.css" rel="stylesheet">
            <link href="/static/css/all.css" rel="stylesheet" type="text/css">
               <link
                    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                    rel="stylesheet">
            <link href="/static/css/sb-admin-2.min.css" rel="stylesheet">
            <link href="/static/css/app.css" rel="stylesheet">


        </head>
        <body id="page-top">
            <div id="wrapper">

                <!-- Sidebar -->
                <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                    <!-- Sidebar - Brand -->
                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                        <div class="sidebar-brand-icon rotate-n-15">
                            <i class="fas fa-laugh-wink"></i>
                        </div>
                        <div class="sidebar-brand-text mx-3">HR PORTAL</div>
                    </a>


                    <!-- Nav Item - Dashboard -->
                    <li class="nav-item">
                        <a class="nav-link" href="/hello">
                            <i class="fas fa-fw fa-tachometer-alt"></i>
                            <span>Test Module page</span></a>
                    </li>

                </ul>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                            <h4>Test Module</h4>
                            <!-- Sidebar Toggle (Topbar) -->
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>


                            <!-- Topbar Navbar -->
                            <ul class="navbar-nav ml-auto">

                                <!-- Nav Item - User Information -->
                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                    </a>
                                    <!-- Dropdown - User Information -->
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                        aria-labelledby="userDropdown">
                                        <a class="dropdown-item" href="#">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Profile
                                        </a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Logout
                                        </a>
                                    </div>
                                </li>

                            </ul>

                        </nav>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container-fluid">
                            <div class="row">
                            <form class="form" action="hello" method="post">
                                <div class="form-group">
                                    <label for="id">ID</label>
                                    <input id="id" type="text" name="id" class="form-control id_record" value="${test_model.id}" disabled>
                                </div>

                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input id="username" type="text" name="username" class="form-control" value="${test_model.username}" disabled>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input id="password" type="text" name="password" class="form-control" value="${test_model.password}" disabled>
                                </div>
                                <a id="edit_btn" class="btn btn-primary" href="#"><i class="fa fa-edit"></i> Edit</a>
                                <a id="cancel_btn" class="btn btn-primary hidden" href="#">Cancel</a>
                                <a id="put_btn" class="btn btn-primary hidden" href="#">Submit</a>
                            </form>
                            </div>


                        </div>
                        <!-- /.container-fluid -->

                    </div>
                    <!-- End of Main Content -->

                    <!-- Footer -->
                    <footer class="sticky-footer bg-white">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright &copy; Beta80 Group</span>
                            </div>
                        </div>
                    </footer>
                    <!-- End of Footer -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <div class="SPINNER_HOLDER hidden">
                <div class="SPINNER">
                </div>
            </div>
        </body>
        <!-- SYSTEM JS -->
        <script src="/static/js/jquery-3.2.1.min.js"></script>
        <script src="/static/js/popper.min.js" ></script>
        <script src="/static/js/bootstrap.min.js" ></script>
        <script src="/static/js/sb-admin-2.min.js" ></script>
        <script src="/static/js/app/app_utils.js"></script>

        <!-- Page JS -->
        <script src="/static/js/app/hello/hello.js"></script>


        <script>
            var doPutUrl = "/hello";
            function enableEdit(e){
                e.preventDefault();
                $('#cancel_btn').removeClass("hidden");
                $('#put_btn').removeClass("hidden");

                $(this).addClass('hidden');
                $("#username").prop('disabled', false);
                $("#password").prop('disabled', false);

            }
            function disableEdit(e){
                e.preventDefault();
                $('#edit_btn').removeClass("hidden");
                $(this).addClass('hidden');
                $('#put_btn').addClass("hidden");
                $("#username").prop('disabled', true);
                $("#password").prop('disabled', true);
            }

            function onClickPut(evt){
                var _btnId = $('#id').val();
                var real_url = doPutUrl + "?id=" + _btnId;
                spinner_on();
                var data = {
                    username: $("#username").val(),
                    password: $("#password").val()
                }
                doPut(data, real_url,
                    function(s){
                        console.log(s);
                        console.log("done");
                        location.reload()
                    },
                    function(err){
                        console.log(err);
                        console.log("fail");
                        location.reload()
                    });
            }
            $( document ).ready(function() {
                $("#edit_btn").on("click", enableEdit);
                $("#cancel_btn").on("click", disableEdit);
                $("#put_btn").on("click", onClickPut);

                console.log( "ready!" );
            });
        </script>
</html>