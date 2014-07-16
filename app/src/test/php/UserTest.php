<?php
require_once dirname(__FILE__) . '/../../www/classes/User.php';

class UserTest extends PHPUnit_Framework_TestCase
{
    public function testCreateUser()
    {
        $user = new User;
        $user->username = 'Bob';

        $this->assertEquals($user->username, 'Bob');
    }
}
?>