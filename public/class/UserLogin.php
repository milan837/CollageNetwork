<?php
class User{
	public $db;
	public $id;

	function __construct($db,$id){
		$this->db=$db;
		$this->id=$id;
	}

	function result(){
		$query=$this->db->query("select * from `user_login` where user_id='".$this->id."'");
		$result=$query->fetch();
		return $result;
	}

	function getUsername(){
		$result=$this->result();
	return $result['username'];
	}

	function getEmail(){
		$result=$this->result();
		return $result['email'];
	}

	function getUsn(){
		$result=$this->result();	
	return $result['usn'];
	}

	function getBranch(){
		$result=$this->result();
	return $result['branch'];
	}

	function getRegisterOn(){
		$result=$this->result();
	return $result['register_on'];
	}
	function getActive(){
		$result=$this->result();
		return $result['active'];
	}
}
?>