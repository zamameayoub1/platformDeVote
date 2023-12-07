export const metadata = {
  title: 'Home ',
  description: 'Page description',
}

import Hero from '@/components/hero'
import Testimonials from '@/components/testimonials'

export default function Home() {
  return (
    <>
      <Hero />
      <Testimonials />
    </>
  )
}
