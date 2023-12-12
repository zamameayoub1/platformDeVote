"use client";
import axios, { AxiosError } from 'axios';
import Link from 'next/link'
import { useState } from 'react'

export default function SignUp() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [cin, setCin] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const handleSubmit = async (e: React.FormEvent) =>{
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/v1/auth/sign-up",
      JSON.stringify({username ,email, password, confirmPassword, cin, phoneNumber }),
      {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: false
      });
      
  }
  catch (err:any) {
    if (err instanceof AxiosError){
      if (err.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.error("Error:", JSON.stringify(err.response.data.errors[0]));
        setErrorMsg(JSON.stringify(err.response.data.errors[0]));
      } else if (err.request) {
        // The request was made but no response was received
        console.error("No response received from the server.");
        setErrorMsg("No response received from the server.");
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error("Error setting up the request:", err.message);
        setErrorMsg("Error setting up the request: " + err.message);
      }
    } else{
      console.error("Unexpected error:", err.message);
      setErrorMsg("Unexpected error: " + err.message);
    }
  }
}
  return (
    <section className="relative">
      <div className="max-w-6xl mx-auto px-4 sm:px-6">
        <div className="pt-32 pb-12 md:pt-40 md:pb-20">

          {/* Page header */}
          <div className="max-w-3xl mx-auto text-center pb-12 md:pb-20">
            <h1 className="h1">Welcome. We exist to make your voice more crucial in shaping the future.</h1>
          </div>

          {/* Form */}
          <div className="max-w-sm mx-auto">
            <form onSubmit={handleSubmit}>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="full-name">Full Name <span className="text-red-600">*</span></label>
                  <input id="fullname"
                  type="text"
                  className="form-input w-full text-gray-300"
                  placeholder="First and last name"
                  onChange = {(event) =>{
                    setUsername(event.currentTarget.value);
                  }}
                  value={username}
                  required />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="company-name">Phone number <span className="text-red-600">*</span></label>
                  <input id="phoneNumber"
                    type="text"
                    className="form-input w-full text-gray-300"
                    placeholder="Your phone number"
                    onChange = {(event) =>{
                    setPhoneNumber(event.currentTarget.value);
                    }}
                    value={phoneNumber}
                    required />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="company-name">CIN <span className="text-red-600">*</span></label>
                  <input id="cin"
                     type="text"
                     className="form-input w-full text-gray-300"
                     placeholder="carte d'identite nationale"
                     onChange = {(event) =>{
                      setCin(event.currentTarget.value);
                    }}
                    value={cin}
                     required />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="email">Email <span className="text-red-600">*</span></label>
                  <input 
                  id="email" 
                  type="email"   
                  className="form-input w-full text-gray-300" 
                  placeholder="Name@gmail.com"
                  onChange = {(event) =>{
                    setEmail(event.currentTarget.value);
                  }}
                  value={email}
                  required 
                  />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="password">Password <span className="text-red-600">*</span></label>
                  <input id="password"
                    type="password"
                    className="form-input w-full text-gray-300"
                    placeholder="Password (at least 10 characters)"
                    onChange = {(event) =>{
                      setPassword(event.currentTarget.value);
                    }}
                    value={password}
                    required />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="password">Password Verification<span className="text-red-600">*</span></label>
                  <input id="passwordVerification"
                    type="password" 
                    className="form-input w-full text-gray-300" 
                    placeholder="Retype your password" 
                    onChange = {(event) =>{
                      setConfirmPassword(event.currentTarget.value);
                    }}
                    value={confirmPassword}
                    required />
                </div>
              </div>
              <div className="text-sm text-gray-500 text-center">
                I agree to be contacted by Vote Responsable <Link href="#" className="underline text-gray-400 hover:text-gray-200 hover:no-underline transition duration-150 ease-in-out">Privacy Policy</Link>.
              </div>
              <div className="flex flex-wrap -mx-3 mt-6">
                <div className="w-full px-3">
                  <button className="btn text-white bg-purple-600 hover:bg-purple-700 w-full">Sign up</button>
                </div>
              </div>
            </form>
            <div className="text-gray-400 text-center mt-6">
              Already have account  <Link href="/signin" className="text-purple-600 hover:text-gray-200 transition duration-150 ease-in-out">Sign in</Link>
            </div>
          </div>

        </div>
      </div>
    </section>
  )
}
