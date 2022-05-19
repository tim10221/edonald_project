<!-- 관리자 로그인 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>관리자 로그인</title>
<link rel="stylesheet" href="/resources/css/adminStyles.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/resources/js/hadminjoin.js"></script>
</head>
<body class="bg-black">
	<div id="layoutAuthentication"
		style="flex-direction: column; min-height: 100%;">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-7">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4 haeder-text">지점장 등록</h3>
								</div>
								<div class="card-body">

									<form action="" id="storeUpdate" method="post">
										<input type="hidden" name="admin_seq"
											value="${storeDetail.admin_seq}">
										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input name="admin_id" class="form-control"
														value="${storeDetail.admin_id}" id="inputId"
														type="text" placeholder="Enter your first name" /> <label
														for="inputFirstName">이메일</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating">
													<input name="admin_name" class="form-control"
														value="${storeDetail.admin_name}" id="inputName"
														type="text" placeholder="Enter your last name" /> <label
														for="inputLastName">이름</label>
												</div>
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input name="admin_password" class="form-control"
														value="${storeDetail.admin_password}" id="inputPassword"
														type="password" placeholder="Enter your first name" /> <label
														for="inputFirstName">비밀번호</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input name="admin_password" class="form-control"
														value="${storeDetail.admin_password}" id="inputPasswordCheck"
														type="password" placeholder="Enter your first name" /> <label
														for="inputFirstName">비밀번호 확인</label>
												</div>
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<input name="store_code" class="form-control"
														value="${storeDetail.store_code}" id="inputPhone"
														type="text" placeholder="Enter your first name" /> <label
														for="inputFirstName">전화번호</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating">
													<input name="store_phone" class="form-control"
														value="${storeDetail.store_phone}" id="storeCode"
														type="text" placeholder="Enter your last name" /> <label
														for="inputLastName">매장번호</label>
												</div>
											</div>
										</div>
										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
													<div class="container">
														<select class="form-control" id="inputGender">
														  <option value="1">남자</option>
														  <option value="2">여자</option>
														</select>
													</div>
												</div>
											</div>
										</div>

										<div class="mt-4 mb-0">
											<div class="d-grid">
												<button type="button" id="click"
													class="btn btn-danger btn-block" onclick="updateDo()">등록</button>
											</div>
										</div>
									</form>
								</div>
								<div class="card-footer text-center py-3">
									<div class="small">
										<a href="index.jsp">메인 페이지</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>

		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2021</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<div id="user_email_check" hidden>${user.user_email }</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="/resources/js/scripts.js"></script>

</body>
</html>