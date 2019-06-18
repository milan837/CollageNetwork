<?php
#------------------------------- login page api ------------------------------

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$app = new \Slim\App;

//----------------------------------- E-mail login api --------------------------------------//

$app->post('/auth/login/email',function(Request $request,Response $response,array $args){
	require 'db.php';
	$email=$request->getParsedBody()['email'];
	$password=md5(trim($request->getParsedBody()['password']));

	if(!empty($email) && !empty($password)){
		$checkQuery=$db->query("select * from `user_login` where email='$email' and password='$password'");
		if($checkQuery->rowCount() == 1){
			$result=$checkQuery->fetch();
			
			$userId=$result['user_id'];
			$status="ok";
			$responseData=array(
				"user_id"=>$userId,
				"username"=>$result['username'],
				"branch"=>$result['branch'],
				"usn"=>$result['usn'],
				"status"=>$status
			);

		}else{
			$status="invalid users";
			$responseData=array(
				"user_id"=>"none",
				"username"=>"none",
				"branch"=>"none",
				"usn"=>"none",
				"status"=>$status
			);
		}
	}else{
		$status="missing field";
		$responseData=array(
				"user_id"=>"none",
				"username"=>"none",
				"branch"=>"none",
				"usn"=>"none",
				"status"=>$status
			);
	}

		return json_encode(array("response"=>$responseData));
});

//----------------------------------- E-mail sign up api --------------------------------------//
$app->post('/auth/signup/email',function(Request $request,Response $response,array $args){
	require "db.php";
	require "functions.php";

	$email=$request->getParsedBody()['email'];
	$password=md5(trim($request->getParsedBody()['password']));

	if(!empty($email) && !empty($password)){
		$checkEmail=$db->query("select * from `user_login` where email='$email' and active='1'");
		if($checkEmail->rowCount() <= 0){
			$insertData=$db->query("insert into `user_login`(email,password) values('$email','$password')");
			$userId=$db->lastInsertId();
			if($insertData){ 
				//$code=rand(1000,99999);
				$code="1234";
				if(verificationUrl($db,$email,$code) == "link updated"){
					$responseData=array(
						"status"=>"ok",
						"user_id"=>$userId,
						"email"=>$email,
						"code"=>$code
					);
					$status="ok";	
				}else{
					$status="verification url is not send";
					$responseData=array(
						"status"=>$status,
						"user_id"=>"none",
						"email"=>"none",
						"code"=>"none"
					);
				}
			}else{
				$status="error insert data";
				$responseData=array(
					"status"=>$status,
					"user_id"=>"none",
					"email"=>"none",
					"code"=>"none"
				);
			}
		}else{
			$status="email already exists";
			$responseData=array(
				"status"=>$status,
				"user_id"=>"none",
				"email"=>"none",
				"code"=>"none"
			);
		}
	}else{
		$status="missing field";
		$responseData=array(
			"status"=>$status,
			"user_id"=>"none",
			"email"=>"none",
			"code"=>"none"
		);
	}

	
	return json_encode(array("response"=>$responseData));
	
});

//----------------------------------- verification api --------------------------------------//
$app->post('/auth/verification/email',function(Request $request,Response $response,array $args){
	require "db.php";

	$email=$request->getParsedBody()['email'];
    $code=$request->getParsedBody()['code'];
    $userId=$request->getParsedBody()['user_id'];

	if(!empty($email) && !empty($code) && !empty($userId)){
		$checkQuery=$db->query("select * from `user_login` where email='$email' and verification_code='$code'");
		if($checkQuery->rowCount() > 0){
			$updateQuery=$db->query("update `user_login` set active='1' where email='$email' and verification_code='$code'");
			if($updateQuery){
				$responseData=array(
					"status"=>"ok",
					"user_id"=>$userId
				);
			}else{
				$responseData=array(
					"status"=>"verification faield",
					"user_id"=>$userId
				);
			}
		}else{
			$responseData=array(
				"status"=>"incorrect code",
				"user_id"=>$userId
			);
		}
	}else{
		$responseData=array(
			"status"=>"missing Field",
			"user_id"=>$userId
		);
	}

	return json_encode(array("response"=>$responseData));
});

//----------------------------------- save user info api --------------------------------------//
$app->post('/auth/save_info',function(Request $request,Response $response,array $args){
	require "include/db.php";
	$user_id=$request->getParsedBody()['user_id'];
	$username=$request->getParsedBody()['username'];
	$file=$_FILES['image'];

	if(!empty($user_id) && !empty($username) && !empty($file)){
		//naming file
		$imageName="profile_".rand(1000,99999)."_".$username."_".$user_id."_".$file['name'];
		$fType=explode(".",$file['type']);
		$type=end($fType);

		//validation of image file 
		if($type == "image/jpeg" || $type == "image/jpg" || $type=="image/png"){
			$uploads="../../../uploads/profile";
			if(is_dir($uploads)){
				if(move_uploaded_file($_FILES['image']['tmp_name'], $uploads."/".$imageName)){
					$updateQuery=$db->query("update `users_login` set username='$username',profile_picture='$imageName' where user_id='$user_id'");
					if($updateQuery){
						$responseData=array(
							"user_id"=>$user_id,
							"username"=>$username,
							"image_url"=>"http://192.168.137.1/debate/uploads/profile/".$imageName,
							"status"=>"ok"
						);
					}else{
						$responseData=array(
							"user_id"=>"none",
							"username"=>"none",
							"status"=>"cannot upload"
						);
					}
				}else{
					$responseData=array(
						"user_id"=>"none",
						"username"=>"none",
						"status"=>"faield uploaded"
					);
				}
			}else{
				$responseData=array(
					"user_id"=>"none",
					"username"=>"none",
					"status"=>"dir not found"
				);
			}
		}else{
			$responseData=array(
					"user_id"=>"none",
					"username"=>"none",
					"status"=>"file not supported"
				);
		}
	}else{
		$responseData=array(
			"user_id"=>"none",
			"username"=>"none",
			"status"=>"missing fields"
		);
	}

	return json_encode(array("response"=>$responseData));
});

//----------------------------------- forget password api --------------------------------------//
$app->get('/auth/forgetpassword/{email}',function(Request $request,Response $response,array $args){
	require "db.php";
	require "functions.php";
	$email=$args['email'];

	if(!empty($email)){
		$checkEmailQuery=$db->query("select * from `user_login` where email='$email' and active=1");
		if($checkEmailQuery->rowCount() == 1){
			if(verificationUrl($db,$email) == "link updated"){
				$status="ok";
			}else{
				$status="email not send";
			}
		}else{
			$status="email not exits";
		}
	}else{
		$status="email missing";
	}

	return json_encode(array("response"=>$status));
});

//----------------------------------- change password api --------------------------------------//
$app->post('/auth/changepassword',function(Request $request,Response $response,array $args){
	require "db.php";

	$oldPassword=md5(trim($request->getParsedBody()['old_password']));
	$newPassword=md5(trim($request->getParsedBody()['new_password']));
	$userId=$request->getParsedBody()['user_id'];

	if(!empty($oldPassword) && !empty($newPassword) && !empty($userId)){
		$checkQuery=$db->query("select * from `user_login` where user_id='$userId' and password='$oldPassword'");
		if($checkQuery->rowCount() == 1){
			$updatePassword=$db->query("update `user_login` set password='$newPassword' where user_id='$userId'");
			if($updatePassword){
				$responseData=array("status"=>"ok");
			}else{
				$responseData=array("status"=>"update_error");
			}
		}else{
			$responseData=array("status"=>"password not match");
		}
	}else{
		$responseData=array("status"=>"missing fields");
	}

	return json_encode(array("response"=>$responseData));
});

//-------------------------------- change username----------------------------------
$app->post('/auth/update/username',function(Request $request,Response $response,array $arg){
	$userId=$request->getParsedBody()['user_id'];
	$username=$request->getParsedBody()['username'];
	$usn=$request->getParsedBody()['usn'];
	$branch=$request->getParsedBody()['branch'];

	require "db.php";
	
	if($userId != ""  && $username != ""){
		$query=$db->query("update `user_login` set username='$username',usn='$usn',branch='$branch' where user_id='$userId'");
		if($query){
			$responseData=array(
				"status"=>"ok"
			);
		}else{
			$responseData=array(
				"status"=>"update failed"
			);
		}
	}else{
		$responseData=array(
			"status"=>"missing fields"
		);
	}
	
	return json_encode(array("response"=>$responseData));
});

$app->run(); 