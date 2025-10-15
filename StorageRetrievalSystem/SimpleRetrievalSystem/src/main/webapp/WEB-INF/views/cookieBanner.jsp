<%-- 
    Document   : cookieBanner
    Created on : 15-Oct-2025, 11:21:54 am
    Author     : hcdc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/cookieBanner.css" />
<!-- cookieBanner.jsp -->
<div id="cookie-banner" class="cookie-banner">
    <p>We use cookies to improve your experience. By clicking "Accept", you agree to our use of cookies.</p>

    <button class="toggle-policy" id="toggle-policy-btn">Learn more about our policies</button>
    <div class="policy-info" id="policy-info">
        <p>
            We use cookies to personalize content, provide social media features, and analyze our traffic.
            You can manage your cookie preferences or learn more about our <a href="/privacy-policy" target="_blank" style="color:#fff;text-decoration:underline;">Privacy Policy</a> and <a href="/cookie-policy" target="_blank" style="color:#fff;text-decoration:underline;">Cookie Policy</a>.
        </p>
    </div>

    <button id="accept-btn">Accept</button>
    <button id="reject-btn">Reject</button>
</div>


<script>
    const cookieBanner = document.getElementById('cookie-banner');
    const acceptBtn = document.getElementById('accept-btn');
    const rejectBtn = document.getElementById('reject-btn');
    const togglePolicyBtn = document.getElementById('toggle-policy-btn');
    const policyInfo = document.getElementById('policy-info');
    const navLinks = document.querySelectorAll('.nav-link');

    function hasCookieDecision() {
        return localStorage.getItem('cookieDecision') !== null;
    }

    function blockNavigation(event) {
        event.preventDefault();
        alert('Please accept or reject cookies to continue browsing.');
    }

    function disableNav() {
        navLinks.forEach(link => {
            link.addEventListener('click', blockNavigation);
            link.style.pointerEvents = 'auto';
            link.style.color = '#999';
            link.style.cursor = 'not-allowed';
        });
    }

    function enableNav() {
        navLinks.forEach(link => {
            link.removeEventListener('click', blockNavigation);
            link.style.pointerEvents = 'auto';
            link.style.color = '';
            link.style.cursor = 'pointer';
        });
    }

    window.onload = () => {
        if (hasCookieDecision()) {
            cookieBanner.style.display = 'none';
            enableNav();
        } else {
            cookieBanner.style.display = 'block';
            setTimeout(() => {
                cookieBanner.classList.add('show'); // Slide up animation
            }, 100); // slight delay for animation effect
            disableNav();
        }
    };

    acceptBtn.onclick = () => {
        localStorage.setItem('cookieDecision', 'accepted');
        cookieBanner.classList.remove('show');
        setTimeout(() => {
            cookieBanner.style.display = 'none';
            enableNav();
        }, 500); // match with transition duration
    };

    rejectBtn.onclick = () => {
        localStorage.setItem('cookieDecision', 'rejected');
        cookieBanner.classList.remove('show');
        setTimeout(() => {
            cookieBanner.style.display = 'none';
            enableNav();
        }, 500);
    };

    togglePolicyBtn.onclick = () => {
        cookieBanner.classList.toggle('expanded');
        if (cookieBanner.classList.contains('expanded')) {
            togglePolicyBtn.textContent = 'Hide policy details';
        } else {
            togglePolicyBtn.textContent = 'Learn more about our policies';
        }
    };




</script>
