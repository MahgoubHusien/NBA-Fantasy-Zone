"use client";   

import React, { ReactNode, useEffect, useRef, useState } from 'react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { SIDENAV_ITEMS } from "@/constants";
import { SideNavItems } from '@/types';
import { Icon } from '@iconify/react';
import { motion, useCycle } from 'framer-motion';

const HeaderMobile = () => {
  return (
    <div>HeaderMobile</div>
  )
}

export default HeaderMobile