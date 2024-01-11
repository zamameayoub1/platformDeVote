import {
  Input,
  Button,
  Typography,
} from "@material-tailwind/react";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios, { AxiosError } from "axios";


export function SignUp() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [cin, setCin] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const handleSubmit = async (e) =>{
    e.preventDefault();
    try {
      console.log("success")
      const response = await axios.post("http://localhost:8080/api/v1/auth/sign-up",
      JSON.stringify({username ,email, password, confirmPassword, cin, phoneNumber }),
      {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: false
      });
      console.log("user registred");
      navigate("/sign-in")
    }
    catch (err) {
      console.log("error")
      if (err instanceof AxiosError){
        if (err.response) {
          // The request was made and the server responded with a status code
          console.error("Error:", JSON.stringify(err.response.data.errors[0]));
          setErrorMsg(JSON.stringify(err.response.data.errors[0]));
        } else if (err.request) {
          // The request was made but no response was received
          setErrorMsg("No response received from the server.");
        } else {
          // Something happened in setting up the request that triggered an Error
          setErrorMsg("Error setting up the request: " + err.message);
        }
      } else{
        console.error("Unexpected error:", err.message);
        setErrorMsg("Unexpected error: " + err.message);
      }
    }
  }
  return (
    <section className="m-8 flex">
            <div className="w-2/5 h-full hidden lg:block">
        <img
          src="/img/pattern.png"
          className="h-full w-full object-cover rounded-3xl"
        />
      </div>
      <div className="w-full lg:w-3/5 flex flex-col items-center justify-center">
        <div className="text-center">
          <div className="mb-1 flex flex-col gap-6">
            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              full name
            </Typography>
            <Input
              size="lg"
              placeholder="example example"
              className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              labelProps={{
                className: "before:content-none after:content-none",
              }}
              value={username}
              onChange = {(event) =>{setUsername(event.currentTarget.value);}}
              />
            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              phone number
            </Typography>
            <Input
              size="lg"
              placeholder="0xxxxxxxxx"
              className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              labelProps={{
                className: "before:content-none after:content-none",
              }}
              value={phoneNumber}
              onChange = {(event) =>{setPhoneNumber(event.currentTarget.value);}}
            />
            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              CIN
            </Typography>
            <Input
              size="lg"
              placeholder="XXXXXX"
              className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              labelProps={{
                className: "before:content-none after:content-none",
              }}
              value={cin}
              onChange = {(event) =>{setCin(event.currentTarget.value);}}
            />
            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              email
            </Typography>
            <Input
              size="lg"
              placeholder="name@mail.com"
              className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              labelProps={{
                className: "before:content-none after:content-none",
              }}
              value={email}
              onChange = {(event) =>{setEmail(event.currentTarget.value);}}
            />
            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              password
            </Typography>
            <Input
              size="lg"
              placeholder="name@mail.com"
              type="password"
              className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              labelProps={{
                className: "before:content-none after:content-none",
              }}
              value={password}
              onChange = {(event) =>{setPassword(event.currentTarget.value);}}
            />
            <Typography variant="small" color="blue-gray" className="-mb-3 font-medium">
              confirm password
            </Typography>
            <Input
              size="lg"
              type="password"
              placeholder="name@mail.com"
              className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
              labelProps={{
                className: "before:content-none after:content-none",
              }}
              value={confirmPassword}
              onChange = {(event) =>{setConfirmPassword(event.currentTarget.value);}}
            />
          </div>
          <Button className="mt-6" fullWidth>
            Register Now
          </Button>
          <Typography variant="paragraph" className="text-center text-blue-gray-500 font-medium mt-4">
            Already have an account?
            <Link to="/sign-in" className="text-gray-900 ml-1">Sign in</Link>
          </Typography>
        </form>
      </div>
    </section>
  );
}

export default SignUp;
