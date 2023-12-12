"use client";
import axios from 'axios';
import Link from 'next/link'
import { useEffect, useRef, useState } from 'react'

export default function SignIn() {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [errMsg, setErrMsg] = useState<string>("");
  const [success, setSuccess] = useState<boolean>(false);
  const userRef = useRef<HTMLInputElement | null>(null);
  const errRef = useRef<HTMLDivElement | null>(null);
  useEffect(() => {
    userRef.current?.focus();
  }, []);

  useEffect(() => {
    setErrMsg('');
  }, [email, password]);

  const handleSubmit = async(e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/v1/auth/sign-in",
      JSON.stringify({ email, password }),
      {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: false
      });
      console.log(JSON.stringify(response?.data));
      const accessToken = response?.data?.access_token;
      console.log(accessToken);
      if (accessToken !== null && accessToken !== "null") {
        setEmail("");
        setPassword("");
        setSuccess(true);
    } else {
        setErrMsg("wrong password or username")
        setSuccess(false);
    }
    }
    catch (err) {
      console.error(err);
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
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="email">Email</label>
                  <input 
                    id="email"
                    type="email"
                    ref={userRef}
                    className="form-input w-full text-gray-300"
                    placeholder="Name@gmail.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <label className="block text-gray-300 text-sm font-medium mb-1" htmlFor="password">Password</label>
                  <input 
                    id="password"
                    type="password" 
                    className="form-input w-full text-gray-300" 
                    placeholder="Password (at least 10 characters)"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)} 
                    required />
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mb-4">
                <div className="w-full px-3">
                  <div className="flex justify-between">
                    <Link href="/reset-password" className="text-purple-600 hover:text-gray-200 transition duration-150 ease-in-out">Forgot Password?</Link>
                  </div>
                </div>
              </div>
              <div className="flex flex-wrap -mx-3 mt-6">
                <div className="w-full px-3">
                  <button className="btn text-white bg-purple-600 hover:bg-purple-700 w-full">Sign in</button>
                </div>
              </div>
            </form>
            <div className="text-gray-400 text-center mt-6">
              Don’t you have an account? <Link href="/signup" className="text-purple-600 hover:text-gray-200 transition duration-150 ease-in-out">Sign up</Link>
            </div>
          </div>

        </div>
      </div>
    </section>
  )
}
