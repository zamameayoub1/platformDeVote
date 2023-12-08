import { PropsWithChildren } from "react";
import NavBar from "../components/navbar/navbar";

export default function LayoutMain(props: PropsWithChildren) {
  return (
    <>
      <NavBar />
      {props.children}
    </>
  );
}
